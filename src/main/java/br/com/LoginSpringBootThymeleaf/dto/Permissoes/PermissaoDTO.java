package br.com.LoginSpringBootThymeleaf.dto.Permissoes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PermissaoDTO {

	private Integer id;

	private String permissao;
	
	private String descricao;
	
//	private List<GrupoEntity> grupos = new ArrayList<GrupoEntity>();
	
	public PermissaoDTO(Integer integer, String permissao, String descricao) {
		this.id = integer;
		this.permissao = permissao;
		this.descricao = descricao;
	}
}
