package com.minotauro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.minotauro.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {

	@Query(value = " SELECT S.* FROM sala S WHERE S.chegada = false LIMIT 1 ", nativeQuery = true)
	Optional<Sala> findFirstNaoChegada();

}
