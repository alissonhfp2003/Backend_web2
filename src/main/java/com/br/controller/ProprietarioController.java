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
import com.br.model.Proprietario;
import com.br.repository.ProprietarioRepository;

@RestController
@RequestMapping("/cproprietario/")
@CrossOrigin(origins="*")
public class ProprietarioController {

	@Autowired
	private ProprietarioRepository proprietarioRep;

	@GetMapping("/proprietario")
	public List<Proprietario> listar(){
		return proprietarioRep.findAll();
	}

	@GetMapping("/proprietario/{id}")
	public ResponseEntity<Proprietario> consultar(@PathVariable Long id) {
		Proprietario proprietario = proprietarioRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Proprietario nao encontrado."));
		return ResponseEntity.ok(proprietario);
	}

	@PostMapping("/proprietario")
	public Proprietario inserir(@RequestBody Proprietario proprietario) {
		return proprietarioRep.save(proprietario);
	}

	@PutMapping("/proprietario/{id}")
	public ResponseEntity<Proprietario> alterar(@PathVariable Long id, @RequestBody Proprietario proprietarioAtualizado) {
		Proprietario proprietario = proprietarioRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Proprietario nao encontrado."));

		proprietario.setNome(proprietarioAtualizado.getNome());
		proprietario.setCpfCnpj(proprietarioAtualizado.getCpfCnpj());
		proprietario.setTelefone(proprietarioAtualizado.getTelefone());
		proprietario.setEmail(proprietarioAtualizado.getEmail());
		proprietario.setEndereco(proprietarioAtualizado.getEndereco());

		Proprietario proprietarioSalvo = proprietarioRep.save(proprietario);
		return ResponseEntity.ok(proprietarioSalvo);
	}

	@DeleteMapping("/proprietario/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		Proprietario proprietario = proprietarioRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Proprietario nao encontrado."));

		proprietarioRep.delete(proprietario);
		return ResponseEntity.noContent().build();
	}
}
