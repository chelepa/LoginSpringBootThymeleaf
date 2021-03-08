package br.com.LoginSpringBootThymeleaf.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "TB_USUARIO")
public class UsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_USUARIO")
	private Integer id;
	 
	@Column(name="DS_NOME")
	private String nome;

	@Column(name = "DS_LOGIN")
	private String login;

	@Column(name = "DS_SENHA")
	private String senha;

	@Column(name = "FL_ATIVO")
	private boolean ativo;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
    	name = "TB_USUARIO_GRUPO", 
	    joinColumns = {@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")}, 
	    inverseJoinColumns = {@JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")}
    )
	private List<GrupoEntity> grupos = new ArrayList<GrupoEntity>();
}