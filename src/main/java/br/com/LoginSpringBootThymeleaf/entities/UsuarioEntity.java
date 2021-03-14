package br.com.LoginSpringBootThymeleaf.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_usuario")
public class UsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Integer id;
	 
	@Column(name="ds_nome")
	private String nome;

	@Column(name = "ds_login")
	private String login;

	@Column(name = "ds_senha")
	private String senha;

	@Column(name = "fl_ativo")
	private boolean ativo;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
    	name = "tb_usuario_grupo", 
	    joinColumns = {@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")}, 
	    inverseJoinColumns = {@JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")}
    )
	private List<GrupoEntity> grupos = new ArrayList<GrupoEntity>();
}