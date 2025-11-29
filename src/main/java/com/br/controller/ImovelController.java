package com.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.exception.ResourceNotFoundException;
import com.br.model.*;
import com.br.repository.ImovelRepository;

@RestController
@RequestMapping("/cimovel/")
@CrossOrigin(origins="*")
public class ImovelController {

	@Autowired
	private ImovelRepository imovelrep;


	@GetMapping("/imovel")
	public List<Imovel> listar(){

		return imovelrep.findAll();

	}

	@GetMapping("/imovel/{id}")
	public ResponseEntity<Imovel> consultar(@PathVariable Long id) {

		Imovel imovel = imovelrep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Imovel nao encontrado."));

		return ResponseEntity.ok(imovel);
	}

	@PostMapping("/imovel")
	public Imovel inserir(@RequestBody Imovel imovel) {
		return imovelrep.save(imovel);
	}

	@PutMapping("/imovel/{id}")
	public ResponseEntity<Imovel> alterar(@PathVariable Long id, @RequestBody Imovel imovelAtualizado) {

		Imovel imovel = imovelrep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Imovel nao encontrado."));

		imovel.setEndereco(imovelAtualizado.getEndereco());
		imovel.setTipo(imovelAtualizado.getTipo());
		imovel.setDataConstrucao(imovelAtualizado.getDataConstrucao());
		imovel.setPrecoVenda(imovelAtualizado.getPrecoVenda());
		imovel.setMobiliado(imovelAtualizado.isMobiliado());
		imovel.setQuantidade(imovelAtualizado.getQuantidade());

		Imovel imovelSalvo = imovelrep.save(imovel);

		return ResponseEntity.ok(imovelSalvo);
	}

	@DeleteMapping("/imovel/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {


		Imovel imovel = imovelrep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Imovel nao encontrado."));

		imovelrep.delete(imovel);

		return ResponseEntity.noContent().build();
	}

}
