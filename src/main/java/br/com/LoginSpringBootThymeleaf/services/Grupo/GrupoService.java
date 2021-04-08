package br.com.LoginSpringBootThymeleaf.services.Grupo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoDTO;
import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoResponseDTO;
import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoResquestDTO;
import br.com.LoginSpringBootThymeleaf.dto.Permissoes.PermissaoDTO;
import br.com.LoginSpringBootThymeleaf.dto.Permissoes.PermissaoResponseDTO;
import br.com.LoginSpringBootThymeleaf.entities.GrupoEntity;
import br.com.LoginSpringBootThymeleaf.entities.PermissaoEntity;
import br.com.LoginSpringBootThymeleaf.entities.UsuarioEntity;
import br.com.LoginSpringBootThymeleaf.repositories.GrupoRepository;
import br.com.LoginSpringBootThymeleaf.repositories.PermissaoRepository;

@Service
@Transactional
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
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
		response.setCodigo(grupo.getCodigo());
		response.setNome(grupo.getNome());
		response.setDescricao(grupo.getDescricao());
		response.setPermissoes(buscaPermissoes(grupo.getPermissoes()));
		grupoRepository.saveAndFlush(response);
	}
	
	private List<PermissaoEntity> buscaPermissoes(List<Integer> permissoes) {
		List<PermissaoEntity> listpermissoes = new ArrayList<>();
		for (Integer permissaoEntity : permissoes) {
			if (permissaoEntity != null) {
				Optional<PermissaoEntity> response = permissaoRepository.findById(permissaoEntity);
				listpermissoes.add(response.get());
			}
		}
		return listpermissoes;
	}

	public List<GrupoEntity> findByUsuariosGrupos(UsuarioEntity usuarioEntity){
		return grupoRepository.findByUsuarios(usuarioEntity);
	}
	
	public Optional<GrupoEntity> gruposById(Integer codigoGrupo){
		return grupoRepository.findById(codigoGrupo);
	}

	public void delete(Integer codigoGrupo) {
		grupoRepository.deleteById(codigoGrupo);
	}

	public List<PermissaoResponseDTO> permissaoSelected(List<PermissaoDTO> permissoes) {
		List<PermissaoResponseDTO> response = listPermissaoCheck();
		response.forEach(permissao -> {
			permissoes.forEach(permissaoSelecionada -> { 
				if(permissaoSelecionada != null && permissao.getId().equals(permissaoSelecionada.getId())){
					permissao.setChecked(true);
				}
			});
		});
		
		return response;
	}
	
	private List<PermissaoResponseDTO> listPermissaoCheck() {
		List<PermissaoResponseDTO> response = new ArrayList<>();
		List<PermissaoEntity> listPermissoes = permissaoRepository.findAll();
		listPermissoes.forEach(item -> response.add(modelMapper.map(item, PermissaoResponseDTO.class)));
		return response;
	}

	public void saveUpdateGrupo(GrupoResquestDTO request) {
		Optional<GrupoEntity> grupo = gruposById(request.getCodigo());
		
		if(grupo.isPresent()) {
			grupo.get().setCodigo(request.getCodigo());
			grupo.get().setDescricao(request.getDescricao());
			grupo.get().setNome(request.getNome());
			grupo.get().setPermissoes(getListPermissaoById(request.getPermissoes()));
		}
	}

	private List<PermissaoEntity> getListPermissaoById(List<Integer> permissoes) {
		List<PermissaoEntity> listPermissoes = new ArrayList<>();
		permissoes.stream().forEach(item -> {
			if (Objects.nonNull(item)) {
				listPermissoes.add(permissaoRepository.findById(item).get());
			}
		});
		return listPermissoes;
	}
}
