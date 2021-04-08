package br.com.LoginSpringBootThymeleaf.services.Grupo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoDTO;
import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoResponseDTO;
import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoResquestDTO;
import br.com.LoginSpringBootThymeleaf.entities.GrupoEntity;
import br.com.LoginSpringBootThymeleaf.entities.UsuarioEntity;
import br.com.LoginSpringBootThymeleaf.repositories.GrupoRepository;

@Service
@Transactional
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
    @Autowired
    private ModelMapper modelMapper;
	
 
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
			
			GrupoResponseDTO grupoResponse = modelMapper.map(grupo, GrupoResponseDTO.class);
			response.add(grupoResponse);
			
		});
		
		return response;
	}
	
	public GrupoResponseDTO getGrupoById(Integer idGrupo) {
		GrupoResponseDTO response = new GrupoResponseDTO();
		Optional<GrupoEntity> grupo = grupoRepository.findById(idGrupo);
		modelMapper.map(grupo.get(), response);
		return response;
	}
	
	public void saveGrupo(GrupoResquestDTO grupo) {
		GrupoEntity response = new GrupoEntity();
		modelMapper.map(grupo, response);
		grupoRepository.saveAndFlush(response);
	}
	
	public List<GrupoEntity> findByUsuariosGrupos(UsuarioEntity usuarioEntity){
		return grupoRepository.findByUsuarios(usuarioEntity);
	}
	
	public Optional<GrupoEntity> gruposById(Integer codigoGrupo){
		return grupoRepository.findById(codigoGrupo);
	}

	public void delete(Integer codigoGrupo) {
		Optional<GrupoEntity> grupo = gruposById(codigoGrupo);
		if (grupo.isPresent()) {
			grupoRepository.delete(grupo.get());
		}
		
	}
}
