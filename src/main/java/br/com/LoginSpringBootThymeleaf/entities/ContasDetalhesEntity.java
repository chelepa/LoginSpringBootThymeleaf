package br.com.LoginSpringBootThymeleaf.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_contas_detalhes")
public class ContasDetalhesEntity  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contas_detalhes")
	private Integer codigo;
 
	@Column(name="descricao")
	private String descricao;
 
	@Column(name="valor")
	private LocalDate valor;
	
	@Column(name="pagemento")
	private Boolean flagpagamento;
	
	@ManyToOne(cascade= CascadeType.PERSIST)
	@JoinColumn(name = "id_contas")
	private ContasEntity contas;
	
	@ManyToOne(cascade= CascadeType.PERSIST)
	@JoinColumn(name = "id_files")
	private FilesEntity files;
		
}
