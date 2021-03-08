package br.com.LoginSpringBootThymeleaf.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.LoginSpringBootThymeleaf.dto.UsuarioDTO;
import br.com.LoginSpringBootThymeleaf.dto.UsuarioSecurityDTO;
import br.com.LoginSpringBootThymeleaf.entities.GrupoEntity;
import br.com.LoginSpringBootThymeleaf.entities.UsuarioEntity;
import br.com.LoginSpringBootThymeleaf.repositories.GrupoRepository;
import br.com.LoginSpringBootThymeleaf.repositories.PermissaoRepository;
import br.com.LoginSpringBootThymeleaf.repositories.UsuarioRepository;

@Component
public class UsuarioService  implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
 
	@Autowired
	private GrupoRepository grupoRepository; 
 
	@Autowired
	private PermissaoRepository permissaoRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws BadCredentialsException,DisabledException {
 
		UsuarioEntity usuarioEntity = usuarioRepository.findByLogin(login);
 
		if(usuarioEntity == null)
			throw new BadCredentialsException("Usuário não encontrado no sistema!");
 
		if(!usuarioEntity.isAtivo())
			throw new DisabledException("Usuário não está ativo no sistema!");
 
		return new UsuarioSecurityDTO(
				usuarioEntity.getLogin(), 
				usuarioEntity.getSenha(), 
				usuarioEntity.isAtivo(), 
				this.buscarPermissoesUsuario(usuarioEntity));
	}
	
	public void salvarUsuario(UsuarioDTO user){
		UsuarioEntity usuarioEntity =  new UsuarioEntity();
		usuarioEntity.setAtivo(true);
		usuarioEntity.setLogin(user.getLogin());
		usuarioEntity.setNome(user.getNome());
		usuarioEntity.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
		usuarioEntity.setGrupos(buscarGruposUsers(user));
		usuarioRepository.save(usuarioEntity);
	}
	
	public List<UsuarioDTO> consultarUsuarios(){
		 
		List<UsuarioDTO> usuariosModel = new ArrayList<UsuarioDTO>();
 
		List<UsuarioEntity> usuariosEntity = usuarioRepository.findAll();
 
		usuariosEntity.forEach(usuarioEntity -> {
			usuariosModel.add(new UsuarioDTO(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getLogin(), null, usuarioEntity.isAtivo(), null));
		});
 
		return usuariosModel;
	}
	
	public void excluir(long codigoUsuario){
		usuarioRepository.deleteById(codigoUsuario);
	}
	
	public void alterarUsuario(UsuarioDTO usuarioModel){
		 
		Optional<UsuarioEntity> usuarioEntity =  usuarioRepository.findById(usuarioModel.getCodigo());
 
		usuarioEntity.get().setAtivo(usuarioModel.isAtivo());
		usuarioEntity.get().setLogin(usuarioModel.getLogin());
		usuarioEntity.get().setNome(usuarioModel.getNome());
		
		if(!StringUtils.isEmpty(usuarioModel.getSenha()))
		 usuarioEntity.get().setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));
 
		usuarioEntity.get().setGrupos(buscarGruposUsers(usuarioModel));
 
		usuarioRepository.saveAndFlush(usuarioEntity.get());
	}
	
	private List<GrupoEntity> buscarGruposUsers(UsuarioDTO user){
		Optional<GrupoEntity> grupoEntity;
		List<GrupoEntity> grupos =  new ArrayList<GrupoEntity>();
		for (Integer codigoGrupo : user.getGrupos()){
			if(codigoGrupo != null){	
				grupoEntity = grupoRepository.findById(codigoGrupo);
				grupos.add(grupoEntity.get());
			}
		}
		return grupos;
	}
	
	private List<GrantedAuthority> buscarPermissoesUsuario(UsuarioEntity usuarioEntity) {
		 
		List<GrupoEntity> grupos = grupoRepository.findByUsuarios(usuarioEntity);
 
		return buscarPermissoesDosGrupos(grupos);
	}
	
	private List<GrantedAuthority> buscarPermissoesDosGrupos(List<GrupoEntity> grupos) {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
		grupos.forEach(grupo -> {
			permissaoRepository.findByGrupos(grupo).forEach(permissao -> {
				auths.add(new SimpleGrantedAuthority(permissao.getPermissao()));
			});
		}); 
		return auths;
	}
}
