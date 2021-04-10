package com.minotauro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minotauro.model.SalaSaida;
import com.minotauro.repositories.SalaSaidaRepository;
import com.minotauro.service.SalaSaidaService;

@RestController
@RequestMapping("api/salaSaida")
@CrossOrigin(origins = "*" ) 
public class SalaSaidaController {
	
	@Autowired
	private SalaSaidaRepository salaSaidaRepository;
	
	@Autowired
	private SalaSaidaService salaSaidaService;
	
	@GetMapping()
	public List<SalaSaida> findAll() {
		return salaSaidaRepository.findAll();
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		this.salaSaidaService.deleteSalaSaida(id);
		return ResponseEntity.ok().build();
	}
	
}
