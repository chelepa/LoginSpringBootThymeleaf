package br.com.LoginSpringBootThymeleaf.services.Usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoDTO;
import br.com.LoginSpringBootThymeleaf.dto.Usuario.UsuarioDTO;
import br.com.LoginSpringBootThymeleaf.dto.Usuario.UsuarioSecurityDTO;
import br.com.LoginSpringBootThymeleaf.entities.GrupoEntity;
import br.com.LoginSpringBootThymeleaf.entities.UsuarioEntity;
import br.com.LoginSpringBootThymeleaf.exceptions.BadCredentialsException;
import br.com.LoginSpringBootThymeleaf.repositories.PermissaoRepository;
import br.com.LoginSpringBootThymeleaf.repositories.UsuarioRepository;
import br.com.LoginSpringBootThymeleaf.services.Grupo.GrupoService;

@Component
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private GrupoService grupoService;

	@Override
	public UserDetails loadUserByUsername(String login) throws DisabledException {

		UsuarioEntity usuarioEntity = usuarioRepository.findByLogin(login);
				
		if (ObjectUtils.isEmpty(usuarioEntity))
			throw new BadCredentialsException("Usuário ou senha invalidos");

		if (!usuarioEntity.isAtivo())
			throw new DisabledException("Usuário não está ativo no sistema!");

		return new UsuarioSecurityDTO(usuarioEntity.getLogin(), usuarioEntity.getSenha(), usuarioEntity.isAtivo(),
				this.buscarPermissoesDosGrupos(usuarioEntity));
	}

	public void salvarUsuario(UsuarioDTO user) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setAtivo(true);
		usuarioEntity.setLogin(user.getLogin());
		usuarioEntity.setNome(user.getNome());
		usuarioEntity.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
		usuarioEntity.setGrupos(buscarGruposUsers(user.getGrupos()));
		usuarioRepository.save(usuarioEntity);
	}

	public List<UsuarioDTO> consultarUsuarios() {

		List<UsuarioDTO> usuariosModel = new ArrayList<>();

		List<UsuarioEntity> usuariosEntity = usuarioRepository.findAll();

		usuariosEntity.forEach(usuarioEntity -> usuariosModel.add(new UsuarioDTO(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getLogin(), null, usuarioEntity.isAtivo(), null)));

		return usuariosModel;
	}
	
	public UsuarioDTO consultarUsuariosbyId(Long codigoUsuario) {
		UsuarioDTO usuariosModel = new UsuarioDTO();

		Optional<UsuarioEntity> usuariosEntity = usuarioRepository.findById(codigoUsuario);
		
		List<Integer> grupos = new ArrayList<>();
		 		
		if (ObjectUtils.allNotNull(usuariosEntity)) {
			
			usuariosEntity.get().getGrupos().forEach(grupo -> grupos.add(grupo.getCodigo())); 
	  
			usuariosModel = new UsuarioDTO(usuariosEntity.get().getId(), usuariosEntity.get().getNome(), usuariosEntity.get().getLogin(), null, usuariosEntity.get().isAtivo(), grupos);
		}

		return usuariosModel;
	}

	public void excluir(long codigoUsuario) {
		usuarioRepository.deleteById(codigoUsuario);
	}

	public void alterarUsuario(UsuarioDTO usuarioModel) {

		Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(usuarioModel.getCodigo());
		
		usuarioEntity.get().setAtivo(usuarioModel.isAtivo());
		usuarioEntity.get().setLogin(usuarioModel.getLogin());
		usuarioEntity.get().setNome(usuarioModel.getNome());
		usuarioEntity.get().setGrupos(buscarGruposUsers(usuarioModel.getGrupos()));
		
		if(!StringUtils.isEmpty(usuarioModel.getSenha())) {
			usuarioEntity.get().setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));
		}
		
		usuarioRepository.saveAndFlush(usuarioEntity.get());
	}
	
	public List<GrupoDTO> setGruposModel(UsuarioDTO usuarioModel) {
		List<GrupoDTO> gruposModel = grupoService.consultarGrupos();
		
		gruposModel.forEach(grupo -> {
			if (ObjectUtils.allNotNull(usuarioModel.getGrupos())) {
				usuarioModel.getGrupos().forEach(grupoSelecionado -> { 
					if(grupoSelecionado != null && grupo.getCodigo().equals(grupoSelecionado)){
						grupo.setChecked(true);
					}
				});
			}
		});
		
		return gruposModel;
	}

	private List<GrupoEntity> buscarGruposUsers(List<Integer> list) {
		Optional<GrupoEntity> grupoEntity;
		List<GrupoEntity> grupos = new ArrayList<>();
		for (Integer codigoGrupo : list) {
			if (codigoGrupo != null) {
				grupoEntity = grupoService.gruposById(codigoGrupo);
				grupos.add(grupoEntity.get());
			}
		}
		return grupos;
	}

	private List<GrantedAuthority> buscarPermissoesDosGrupos(UsuarioEntity usuarioEntity) {
		
		List<GrupoEntity> grupos = grupoService.findByUsuariosGrupos(usuarioEntity);
		
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

		grupos.forEach(grupo ->	permissaoRepository.findByGrupos(grupo).forEach(permissao -> auths.add(new SimpleGrantedAuthority(permissao.getPermissao()))));
		
		return auths;
	}
}
