package com.br.model;

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
@Table(name="proprietario")
public class Proprietario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name="nome", nullable=false, length=100)
	private String nome;

	@Column(name="cpf_cnpj", nullable=false, length=20, unique=true)
	private String cpfCnpj;

	@Column(name="telefone", length=20)
	private String telefone;

	@Column(name="email", length=100)
	private String email;

	@Column(name="endereco", length=200)
	private String endereco;

	// Relacionamento 1:N - Um proprietário pode ter vários imóveis
	@OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL, orphanRemoval = false)
	@JsonManagedReference
	private List<Imovel> imoveis = new ArrayList<>();

	// Construtores
	public Proprietario() {
		super();
	}

	public Proprietario(Long codigo, String nome, String cpfCnpj, String telefone, String email, String endereco) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}
}
