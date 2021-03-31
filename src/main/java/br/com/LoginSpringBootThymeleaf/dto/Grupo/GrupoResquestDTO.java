package br.com.LoginSpringBootThymeleaf.dto.Grupo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoResquestDTO {
	
	private Integer codigo;
	 
	private String nome;

	private String descricao;

}
