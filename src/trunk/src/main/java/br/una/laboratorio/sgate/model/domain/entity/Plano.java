package br.una.laboratorio.sgate.model.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "plano")
public class Plano implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String descricao;
	
	private Long desconto;
	
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(
	   name = "rel_plano_servico",
	   joinColumns = { @JoinColumn(name = "id_plano", referencedColumnName = "id") },
	   inverseJoinColumns = { @JoinColumn(name = "id_servico", referencedColumnName = "id") } )
	private List<Servico> servicos;
	
	@Transient
	private Long preco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getDesconto() {
		return desconto;
	}

	public void setDesconto(Long desconto) {
		this.desconto = desconto;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Long getPreco() {
		return preco;
	}

	public void setPreco(Long preco) {
		this.preco = preco;
	}
	
}
