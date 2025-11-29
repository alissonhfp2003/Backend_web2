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
import com.br.model.Caracteristica;
import com.br.repository.CaracteristicaRepository;

@RestController
@RequestMapping("/ccaracteristica/")
@CrossOrigin(origins="*")
public class CaracteristicaController {

	@Autowired
	private CaracteristicaRepository caracteristicaRep;

	@GetMapping("/caracteristica")
	public List<Caracteristica> listar(){
		return caracteristicaRep.findAll();
	}

	@GetMapping("/caracteristica/{id}")
	public ResponseEntity<Caracteristica> consultar(@PathVariable Long id) {
		Caracteristica caracteristica = caracteristicaRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Caracteristica nao encontrada."));
		return ResponseEntity.ok(caracteristica);
	}

	@PostMapping("/caracteristica")
	public Caracteristica inserir(@RequestBody Caracteristica caracteristica) {
		return caracteristicaRep.save(caracteristica);
	}

	@PutMapping("/caracteristica/{id}")
	public ResponseEntity<Caracteristica> alterar(@PathVariable Long id, @RequestBody Caracteristica caracteristicaAtualizada) {
		Caracteristica caracteristica = caracteristicaRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Caracteristica nao encontrada."));

		caracteristica.setNome(caracteristicaAtualizada.getNome());
		caracteristica.setDescricao(caracteristicaAtualizada.getDescricao());

		Caracteristica caracteristicaSalva = caracteristicaRep.save(caracteristica);
		return ResponseEntity.ok(caracteristicaSalva);
	}

	@DeleteMapping("/caracteristica/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		Caracteristica caracteristica = caracteristicaRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Caracteristica nao encontrada."));

		caracteristicaRep.delete(caracteristica);
		return ResponseEntity.noContent().build();
	}
}
