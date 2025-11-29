package com.br.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

	// Relacionamento N:1 - Muitos imóveis para um proprietário
	@ManyToOne
	@JoinColumn(name = "proprietario_id")
	@JsonBackReference
	private Proprietario proprietario;

	// Relacionamento 1:1 - Um imóvel tem uma documentação
	@OneToOne(mappedBy = "imovel", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Documentacao documentacao;

	// Relacionamento N:N - Um imóvel pode ter várias características
	@ManyToMany
	@JoinTable(
		name = "imovel_caracteristica",
		joinColumns = @JoinColumn(name = "imovel_id"),
		inverseJoinColumns = @JoinColumn(name = "caracteristica_id")
	)
	private Set<Caracteristica> caracteristicas = new HashSet<>();


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

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	public Documentacao getDocumentacao() {
		return documentacao;
	}

	public void setDocumentacao(Documentacao documentacao) {
		this.documentacao = documentacao;
		if (documentacao != null) {
			documentacao.setImovel(this);
		}
	}

	public Set<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(Set<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}







}
