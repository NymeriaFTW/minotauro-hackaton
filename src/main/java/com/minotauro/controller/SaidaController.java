package com.minotauro.controller;

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

import com.minotauro.model.Saida;
import com.minotauro.repositories.SaidaRepository;
import com.minotauro.service.SaidaService;

@RestController
@RequestMapping("api/saida")
@CrossOrigin(origins = "*" )
public class SaidaController {
	
	@Autowired
	private SaidaRepository saidaRepository;
	
	@Autowired
	private SaidaService saidaService;
	
	@GetMapping()
	public List<Saida> findAll() {
		return saidaRepository.findAll();
	}
	
	@PostMapping("/save")
	public Saida saveSaida(@RequestBody Saida saida) {
		return saidaRepository.save(saida);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Saida> editar(@PathVariable("id") long id,
	                                        @RequestBody Saida saida) {
		Saida updated = new Saida();
		updated = this.saidaService.editar(id, saida);
		return ResponseEntity.ok().body(updated);
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		this.saidaService.deleteSaida(id);
		return ResponseEntity.ok().build();
	}
	
	
}
