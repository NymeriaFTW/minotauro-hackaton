package com.minotauro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minotauro.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
