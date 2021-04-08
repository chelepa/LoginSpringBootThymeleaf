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
@Table(name = "tb_permissao")
public class PermissaoEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_permissao")
	private Integer id;
	 
	@Column(name="ds_permissao")
	private String permissao;
	
	@Column(name="ds_descricao")
	private String descricao;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name="tb_permissao_grupo",
			joinColumns=@JoinColumn(name="id_permissao", referencedColumnName="id_permissao"),
			inverseJoinColumns=@JoinColumn(name="id_grupo", referencedColumnName="id_grupo")
		)
	private List<GrupoEntity> grupos = new ArrayList<GrupoEntity>();
}