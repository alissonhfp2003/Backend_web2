package com.br.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name="data_venda", nullable=false)
	private Date dataVenda;

	@Column(name="comprador", nullable=false, length=100)
	private String comprador;

	@Column(name="cpf_comprador", length=20)
	private String cpfComprador;

	@Column(name="forma_pagamento", length=50)
	private String formaPagamento;

	@Column(name="valor_total")
	private double valorTotal;

	@Column(name="observacoes", length=500)
	private String observacoes;

	// Relacionamento 1:N - Uma venda tem vários itens
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ItemVenda> itens = new ArrayList<>();

	// Construtores
	public Venda() {
		super();
	}

	public Venda(Long codigo, Date dataVenda, String comprador, String cpfComprador,
				String formaPagamento, double valorTotal, String observacoes) {
		super();
		this.codigo = codigo;
		this.dataVenda = dataVenda;
		this.comprador = comprador;
		this.cpfComprador = cpfComprador;
		this.formaPagamento = formaPagamento;
		this.valorTotal = valorTotal;
		this.observacoes = observacoes;
	}

	// Getters e Setters
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public String getCpfComprador() {
		return cpfComprador;
	}

	public void setCpfComprador(String cpfComprador) {
		this.cpfComprador = cpfComprador;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
		// Configurar o relacionamento bidirecional
		if (itens != null) {
			for (ItemVenda item : itens) {
				item.setVenda(this);
			}
		}
	}

	// Método auxiliar para adicionar item
	public void addItem(ItemVenda item) {
		itens.add(item);
		item.setVenda(this);
	}

	// Método auxiliar para remover item
	public void removeItem(ItemVenda item) {
		itens.remove(item);
		item.setVenda(null);
	}
}
