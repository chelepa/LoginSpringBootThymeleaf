package br.com.LoginSpringBootThymeleaf.dto.Grupo;

import lombok.Data;

@Data
public class GrupoDTO {

	private Integer codigo;
	private String nome;
	private String descricao;
	private Boolean checked;

	public GrupoDTO() {

	}

	public GrupoDTO(Integer codigo, String nome, String descricao) {
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
	}

	public GrupoDTO(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
