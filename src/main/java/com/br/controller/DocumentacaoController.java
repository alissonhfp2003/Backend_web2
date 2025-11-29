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
import com.br.model.Documentacao;
import com.br.repository.DocumentacaoRepository;

@RestController
@RequestMapping("/cdocumentacao/")
@CrossOrigin(origins="*")
public class DocumentacaoController {

	@Autowired
	private DocumentacaoRepository documentacaoRep;

	@GetMapping("/documentacao")
	public List<Documentacao> listar(){
		return documentacaoRep.findAll();
	}

	@GetMapping("/documentacao/{id}")
	public ResponseEntity<Documentacao> consultar(@PathVariable Long id) {
		Documentacao documentacao = documentacaoRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Documentacao nao encontrada."));
		return ResponseEntity.ok(documentacao);
	}

	@PostMapping("/documentacao")
	public Documentacao inserir(@RequestBody Documentacao documentacao) {
		return documentacaoRep.save(documentacao);
	}

	@PutMapping("/documentacao/{id}")
	public ResponseEntity<Documentacao> alterar(@PathVariable Long id, @RequestBody Documentacao documentacaoAtualizada) {
		Documentacao documentacao = documentacaoRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Documentacao nao encontrada."));

		documentacao.setMatricula(documentacaoAtualizada.getMatricula());
		documentacao.setCartorio(documentacaoAtualizada.getCartorio());
		documentacao.setLivro(documentacaoAtualizada.getLivro());
		documentacao.setFolha(documentacaoAtualizada.getFolha());
		documentacao.setDataRegistro(documentacaoAtualizada.getDataRegistro());
		documentacao.setSituacaoLegal(documentacaoAtualizada.getSituacaoLegal());

		Documentacao documentacaoSalva = documentacaoRep.save(documentacao);
		return ResponseEntity.ok(documentacaoSalva);
	}

	@DeleteMapping("/documentacao/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		Documentacao documentacao = documentacaoRep.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Documentacao nao encontrada."));

		documentacaoRep.delete(documentacao);
		return ResponseEntity.noContent().build();
	}
}
