package br.com.LoginSpringBootThymeleaf.dto.Permissoes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PermissaoResponseDTO {

	private Integer id;

	private String permissao;
	
	private String descricao;
	
	private Boolean checked;
	
	public PermissaoResponseDTO(Integer integer, String permissao, String descricao, Boolean checked) {
		this.id = integer;
		this.permissao = permissao;
		this.descricao = descricao;
		this.checked = checked;
	}
}
