package com.br.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="item_venda")
public class ItemVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "venda_id", nullable = false)
	@JsonBackReference
	private Venda venda;

	@ManyToOne
	@JoinColumn(name = "imovel_id", nullable = false)
	private Imovel imovel;

	@Column(name="valor_parcial", nullable=false)
	private double valorParcial;

	@Column(name="desconto")
	private double desconto;

	@Column(name="observacoes", length=200)
	private String observacoes;

	// Construtores
	public ItemVenda() {
		super();
	}

	public ItemVenda(Long codigo, Venda venda, Imovel imovel, double valorParcial,
					double desconto, String observacoes) {
		super();
		this.codigo = codigo;
		this.venda = venda;
		this.imovel = imovel;
		this.valorParcial = valorParcial;
		this.desconto = desconto;
		this.observacoes = observacoes;
	}

	// Getters e Setters
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public double getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(double valorParcial) {
		this.valorParcial = valorParcial;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
}
