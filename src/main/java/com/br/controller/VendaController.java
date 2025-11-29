package com.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import com.br.model.ItemVenda;
import com.br.model.Venda;
import com.br.repository.VendaRepository;

@RestController
@RequestMapping("/cvenda/")
@CrossOrigin(origins="*")
public class VendaController {

	@Autowired
	private VendaRepository vendaRep;

	@GetMapping("/venda")
	public List<Venda> listar(){
		return vendaRep.findAll();
	}

	@GetMapping("/venda/{id}")
	public ResponseEntity<Venda> consultar(@PathVariable Long id) {
		Venda venda = vendaRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Venda nao encontrada."));
		return ResponseEntity.ok(venda);
	}

	@PostMapping("/venda")
	@Transactional
	public Venda inserir(@RequestBody Venda venda) {
		// Configurar relacionamento bidirecional para cada item
		if (venda.getItens() != null) {
			for (ItemVenda item : venda.getItens()) {
				item.setVenda(venda);
			}
		}
		return vendaRep.save(venda);
	}

	@PutMapping("/venda/{id}")
	@Transactional
	public ResponseEntity<Venda> alterar(@PathVariable Long id, @RequestBody Venda vendaAtualizada) {
		Venda venda = vendaRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Venda nao encontrada."));

		venda.setDataVenda(vendaAtualizada.getDataVenda());
		venda.setComprador(vendaAtualizada.getComprador());
		venda.setCpfComprador(vendaAtualizada.getCpfComprador());
		venda.setFormaPagamento(vendaAtualizada.getFormaPagamento());
		venda.setValorTotal(vendaAtualizada.getValorTotal());
		venda.setObservacoes(vendaAtualizada.getObservacoes());

		// Limpar itens existentes e adicionar novos
		venda.getItens().clear();
		if (vendaAtualizada.getItens() != null) {
			for (ItemVenda item : vendaAtualizada.getItens()) {
				item.setVenda(venda);
				venda.getItens().add(item);
			}
		}

		Venda vendaSalva = vendaRep.save(venda);
		return ResponseEntity.ok(vendaSalva);
	}

	@DeleteMapping("/venda/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		Venda venda = vendaRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Venda nao encontrada."));

		vendaRep.delete(venda);
		return ResponseEntity.noContent().build();
	}
}
