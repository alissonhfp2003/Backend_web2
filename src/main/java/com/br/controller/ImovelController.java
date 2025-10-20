package com.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.exception.ResourceNotFoundException;
import com.br.model.*;
import com.br.repository.ImovelRepository;

@RestController
@RequestMapping("/cimovel/")
@CrossOrigin(origins="*")
public class ImovelController {

	//Cria o repositorio JPA para ser usado aqui no controlador
	@Autowired
	private ImovelRepository imovelrep;


	@GetMapping("/imovel")  //Indica que esse será o nome do endereço a ser chamado
	public List<Imovel> listar(){

		//para chamar o "listar", o endereço completo deverá ser:
		// http://localhost:8080/cimovel/imovel -- usando o protocolo http, método GET

		return imovelrep.findAll();

	}

	@GetMapping("/imovel/{id}")
	public ResponseEntity<Imovel> consultar(@PathVariable Long id) {

		Imovel imovel = imovelrep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Imovel nao encontrado."));

		return ResponseEntity.ok(imovel);
	}



}
