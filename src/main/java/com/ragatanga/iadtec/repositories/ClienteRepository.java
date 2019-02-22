package com.ragatanga.iadtec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragatanga.iadtec.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
