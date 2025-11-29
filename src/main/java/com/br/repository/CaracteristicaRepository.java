package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.model.Caracteristica;

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Long> {

}
