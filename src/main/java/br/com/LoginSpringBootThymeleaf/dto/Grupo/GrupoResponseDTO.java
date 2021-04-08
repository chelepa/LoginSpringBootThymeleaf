package br.com.LoginSpringBootThymeleaf.dto.Grupo;

import java.util.List;

import br.com.LoginSpringBootThymeleaf.dto.Permissoes.PermissaoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoResponseDTO {
	
	private Integer codigo;
 
	private String nome;

	private String descricao;
 
	private List<PermissaoDTO> permissoes;

}