package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.model.Documentacao;

@Repository
public interface DocumentacaoRepository extends JpaRepository<Documentacao, Long> {

}
