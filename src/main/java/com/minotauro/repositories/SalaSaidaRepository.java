package com.minotauro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.minotauro.model.Sala;
import com.minotauro.model.SalaSaida;

public interface SalaSaidaRepository extends JpaRepository<SalaSaida, Long>{

	@Query(" SELECT SS.saida.nome FROM SalaSaida SS WHERE SS.sala = :sala ")
	List<String> findBySalaId(@Param("sala") Sala salaInicial);

}
