package com.br.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="documentacao")
public class Documentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name="matricula", nullable=false, length=50, unique=true)
	private String matricula;

	@Column(name="cartorio", length=100)
	private String cartorio;

	@Column(name="livro", length=20)
	private String livro;

	@Column(name="folha", length=20)
	private String folha;

	@Column(name="data_registro")
	private Date dataRegistro;

	@Column(name="situacao_legal", length=50)
	private String situacaoLegal;

	// Relacionamento 1:1 - Uma documentação pertence a apenas um imóvel
	@OneToOne
	@JoinColumn(name = "imovel_id", unique = true)
	@JsonBackReference
	private Imovel imovel;

	// Construtores
	public Documentacao() {
		super();
	}

	public Documentacao(Long codigo, String matricula, String cartorio, String livro, String folha,
			Date dataRegistro, String situacaoLegal) {
		super();
		this.codigo = codigo;
		this.matricula = matricula;
		this.cartorio = cartorio;
		this.livro = livro;
		this.folha = folha;
		this.dataRegistro = dataRegistro;
		this.situacaoLegal = situacaoLegal;
	}

	// Getters e Setters
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCartorio() {
		return cartorio;
	}

	public void setCartorio(String cartorio) {
		this.cartorio = cartorio;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public String getFolha() {
		return folha;
	}

	public void setFolha(String folha) {
		this.folha = folha;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getSituacaoLegal() {
		return situacaoLegal;
	}

	public void setSituacaoLegal(String situacaoLegal) {
		this.situacaoLegal = situacaoLegal;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
}
