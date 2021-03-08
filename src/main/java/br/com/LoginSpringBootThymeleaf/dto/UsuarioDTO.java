package br.com.LoginSpringBootThymeleaf.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	private long codigo;

	@NotEmpty(message = "O Nome é de preenchimento obrigatório.")
	private String nome;

	@NotEmpty(message = "O Login é de preenchimento obrigatório.")
	private String login;

	@NotEmpty(message = "A Senha é de preenchimento obrigatório.")
	private String senha;

	private boolean ativo;

	@NotEmpty(message = "Não existe nenhum grupo selecionado.")
	private List<Integer> grupos;

	public UsuarioDTO(){
		System.out.println("Passei " + LocalDate.now());
	}

	public UsuarioDTO(Integer codigo, String nome, String login, String senha, boolean ativo, List<Integer> grupos) {
		this.codigo = codigo;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.grupos = grupos;
	}
}
