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
@Table(name = "tb_grupo")
public class GrupoEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_grupo")
	private Integer codigo;
 
	@Column(name="ds_nome")
	private String nome;
 
	@Column(name="ds_descricao")
	private String descricao;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name="tb_usuario_grupo",
		joinColumns=@JoinColumn(name="id_grupo", referencedColumnName="id_grupo"),
		inverseJoinColumns=@JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
	)
	private List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();
 
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name="tb_permissao_grupo",
		joinColumns=@JoinColumn(name="id_grupo", referencedColumnName="id_grupo"),
		inverseJoinColumns=@JoinColumn(name="id_permissao", referencedColumnName="id_permissao")
	)
	private List<PermissaoEntity> permissoes = new ArrayList<PermissaoEntity>();
}
