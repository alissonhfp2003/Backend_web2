package com.br.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="imovel")
public class Imovel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name="endereco")
	private String endereco;

	@Column(name="tipo")
	private String tipo;

	@Column(name="dataconstrucao")
	private Date dataConstrucao;

	@Column(name="precovenda")
	private double precoVenda;

	@Column(name="mobiliado")
	private boolean mobiliado;

	@Column(name="quantidade")
	private int quantidade;


	//Construtor padrao, para a super classe
	public Imovel() {
		super();

	}


	//Construtor com todos os campos
	public Imovel(Long codigo, String endereco, String tipo, Date dataConstrucao, double precoVenda,
			boolean mobiliado, int quantidade) {
		super();
		this.codigo = codigo;
		this.endereco = endereco;
		this.tipo = tipo;
		this.dataConstrucao = dataConstrucao;
		this.precoVenda = precoVenda;
		this.mobiliado = mobiliado;
		this.quantidade = quantidade;
	}



	//Gets and Sets
	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Date getDataConstrucao() {
		return dataConstrucao;
	}


	public void setDataConstrucao(Date dataConstrucao) {
		this.dataConstrucao = dataConstrucao;
	}


	public double getPrecoVenda() {
		return precoVenda;
	}


	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}


	public boolean isMobiliado() {
		return mobiliado;
	}


	public void setMobiliado(boolean mobiliado) {
		this.mobiliado = mobiliado;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}









}
