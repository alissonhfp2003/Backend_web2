package com.br.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="caracteristica")
public class Caracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name="nome", nullable=false, length=50)
	private String nome;

	@Column(name="descricao", length=200)
	private String descricao;

	// Relacionamento N:N - Uma característica pode estar em vários imóveis
	@ManyToMany(mappedBy = "caracteristicas")
	@JsonIgnore
	private Set<Imovel> imoveis = new HashSet<>();

	// Construtores
	public Caracteristica() {
		super();
	}

	public Caracteristica(Long codigo, String nome, String descricao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
	}

	// Getters e Setters
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(Set<Imovel> imoveis) {
		this.imoveis = imoveis;
	}
}
