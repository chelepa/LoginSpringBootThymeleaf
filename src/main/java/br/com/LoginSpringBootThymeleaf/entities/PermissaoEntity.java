package br.com.LoginSpringBootThymeleaf.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_PERMISSAO")
public class PermissaoEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PERMISSAO")
	private Integer id;
	 
	@Column(name="DS_PERMISSAO")
	private String permissao;
	
	@Column(name="DS_DESCRICAO")
	private String descricao;
}