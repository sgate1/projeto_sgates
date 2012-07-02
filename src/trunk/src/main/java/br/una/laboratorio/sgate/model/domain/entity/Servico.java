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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.HALF_DOWN);
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = new BigDecimal(preco);
	}

	public Long getId() {
		return id;
	}
	
}
