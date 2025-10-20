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
import com.br.repository.AutomovelRepository;

@RestController
@RequestMapping("/cautomovel/")
@CrossOrigin(origins="*")
public class AutomovelController {
	
	//Cria o repositorio JPA para ser usado aqui no controlador
	@Autowired
	private AutomovelRepository autorep;
	
	
	@GetMapping("/automovel")  //Indica que esse será o nome do endereço a ser chamado
	public List<Automovel> listar(){
		
		//para chamar o "listar", o endereço completo deverá ser:
		// http://localhost:8080/cautomovel/automovel -- usando o protocolo http, método GET
		
		return autorep.findAll();
		
	}
	
	@GetMapping("/automovel/{id}")
	public ResponseEntity<Automovel> consultar(@PathVariable Long id) {
		
		Automovel auto = autorep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Automovel nao encontrado."));
			
		return ResponseEntity.ok(auto);
	}
	
	

}
