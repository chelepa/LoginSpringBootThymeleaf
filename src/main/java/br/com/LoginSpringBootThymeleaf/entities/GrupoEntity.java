package br.com.LoginSpringBootThymeleaf.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_GRUPO")
public class GrupoEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name="ID_GRUPO")
	private Integer codigo;
 
	@Column(name="DS_NOME")
	private String nome;
 
	@Column(name="DS_DESCRICAO")
	private String descricao;
	
	@ManyToMany
	@JoinTable(
		name="TB_USUARIO_GRUPO",
		joinColumns=@JoinColumn(name="ID_GRUPO", referencedColumnName="ID_GRUPO"),
		inverseJoinColumns=@JoinColumn(name="ID_USUARIO", referencedColumnName="ID_USUARIO")
	)
	private List<UsuarioEntity> usuarios;
 
	@ManyToMany
	@JoinTable(
		name="TB_PERMISSAO_GRUPO",
		joinColumns=@JoinColumn(name="ID_GRUPO", referencedColumnName="ID_GRUPO"),
		inverseJoinColumns=@JoinColumn(name="ID_PERMISSAO", referencedColumnName="ID_PERMISSAO")
	)
	private List<PermissaoEntity> permissoes;
}
