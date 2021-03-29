package br.com.LoginSpringBootThymeleaf.services.Grupo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoDTO;
import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoResponseDTO;
import br.com.LoginSpringBootThymeleaf.dto.Permissoes.PermissaoDTO;
import br.com.LoginSpringBootThymeleaf.entities.GrupoEntity;
import br.com.LoginSpringBootThymeleaf.entities.UsuarioEntity;
import br.com.LoginSpringBootThymeleaf.repositories.GrupoRepository;

@Service
@Transactional
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
 
	@Transactional(readOnly = true)
	public List<GrupoDTO> consultarGrupos(){
 
		List<GrupoDTO> gruposModel =  new ArrayList<GrupoDTO>();
 
		List<GrupoEntity> gruposEntity = grupoRepository.findAll();
 
		gruposEntity.forEach(grupo -> { 
		   gruposModel.add(new GrupoDTO(grupo.getCodigo(), grupo.getDescricao()));
		   return;
	    });
 
		return gruposModel;
	}
	
	public List<GrupoResponseDTO> findAllGrupos(){
		List<GrupoResponseDTO> response = new ArrayList<>();
		
		List<GrupoEntity> grupos = grupoRepository.findAll();
		
		grupos.stream().forEach(grupo -> {
			
			List<PermissaoDTO> permissoes = new ArrayList<>();
			grupo.getPermissoes().forEach(item -> permissoes.add(new PermissaoDTO(item.getId(), item.getPermissao(), item.getDescricao())));
			
			GrupoResponseDTO grupoResponse = new GrupoResponseDTO();
			grupoResponse.setCodigo(grupo.getCodigo());
			grupoResponse.setNome(grupo.getNome());
			grupoResponse.setDescricao(grupo.getDescricao());
			grupoResponse.setPermissoes(permissoes);
			response.add(grupoResponse);
		});
		
		return response;
	}
	
	public List<GrupoEntity> findByUsuariosGrupos(UsuarioEntity usuarioEntity){
		return grupoRepository.findByUsuarios(usuarioEntity);
	}
	
	public Optional<GrupoEntity> gruposById(Integer codigoGrupo){
		return grupoRepository.findById(codigoGrupo);
	}
}
