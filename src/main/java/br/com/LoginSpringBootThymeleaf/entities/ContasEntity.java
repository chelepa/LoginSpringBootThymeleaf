package br.com.LoginSpringBootThymeleaf.entities;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "tb_contas")
public class ContasEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contas")
	private Integer codigo;
 
	@Column(name="nm_descricao")
	private String descricao;
 
	@Column(name="valor")
	private LocalDate valor;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) 
	@JoinTable(
		name="tb_contas_usuario",
		joinColumns=@JoinColumn(name="id_contas", referencedColumnName="id_contas"),
		inverseJoinColumns=@JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
	)
	private List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();
	
}
