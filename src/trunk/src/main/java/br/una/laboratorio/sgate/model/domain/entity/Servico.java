package br.una.laboratorio.sgate.model.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servico")
public class Servico implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String titulo;
	private String descricao;
	private BigDecimal preco;
	
	public Servico(){
	}
	
	public Servico( Long id ){
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Servico setTitulo(String titulo) {
		this.titulo = titulo;
		return this;
	}

	public String getDescricao() {
		return descricao;
	}

	public Servico setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.HALF_DOWN);
	}

	public Servico setPreco(BigDecimal preco) {
		this.preco = preco;
		return this;
	}
	
	public Servico setPreco(Double preco) {
		this.preco = new BigDecimal(preco);
		return this;
	}

}
