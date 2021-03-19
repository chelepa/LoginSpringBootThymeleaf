package br.com.LoginSpringBootThymeleaf.services.Grupo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoDTO;
import br.com.LoginSpringBootThymeleaf.entities.GrupoEntity;
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
}
