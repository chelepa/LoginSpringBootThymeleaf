package br.com.LoginSpringBootThymeleaf.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
	
	private String nome;

	private String login;

	private String senha;

	private boolean ativo;

	private List<Integer> grupos;

	public UsuarioRequestDTO(){
		System.out.println("Passei " + LocalDate.now());
	}

	public UsuarioRequestDTO(String nome, String login, String senha, boolean ativo, List<Integer> grupos) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.grupos = grupos;
	}

}
