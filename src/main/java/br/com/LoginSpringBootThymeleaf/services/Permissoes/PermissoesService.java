package br.com.LoginSpringBootThymeleaf.services.Permissoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.LoginSpringBootThymeleaf.dto.Permissoes.PermissaoDTO;
import br.com.LoginSpringBootThymeleaf.entities.PermissaoEntity;
import br.com.LoginSpringBootThymeleaf.repositories.PermissaoRepository;

@Service
public class PermissoesService {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public List<PermissaoDTO> getAllPermissao() {
		
		List<PermissaoDTO> permissaoDTO = new ArrayList<>();
		
		List<PermissaoEntity> permissaoEntity = permissaoRepository.findAll();
		
		permissaoEntity.stream().forEach(permissao -> permissaoDTO.add(new PermissaoDTO(permissao.getId(), permissao.getPermissao(), permissao.getDescricao())));
		
		return permissaoDTO;
	}
	
	public void savePermissao(PermissaoDTO permissao) {
		
		PermissaoEntity permissaoEntity = new PermissaoEntity();
		
		permissaoEntity.setPermissao(permissao.getPermissao());
		permissaoEntity.setDescricao(permissao.getDescricao());
		
		permissaoRepository.saveAndFlush(permissaoEntity);
	}
	
	public void delete(Integer id) {
		Optional<PermissaoEntity> permissaoEntity = permissaoRepository.findById(id);
		
		if (ObjectUtils.allNotNull(permissaoEntity)) {
			permissaoRepository.delete(permissaoEntity.get());
		}
	}

}
