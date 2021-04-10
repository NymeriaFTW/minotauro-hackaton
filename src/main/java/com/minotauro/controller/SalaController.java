package com.minotauro.controller;

import java.util.Optional;

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

import com.minotauro.model.Sala;
import com.minotauro.repositories.SalaRepository;
import com.minotauro.service.SalaService;

@RestController
@RequestMapping("api/sala")
@CrossOrigin(origins = "*" )
public class SalaController {
	
	@Autowired
	private SalaRepository salaRepository;
	
	@Autowired
	private SalaService salaService;
	
	@GetMapping()
	public Sala iniciar() {
		double inicio = Math.floor(Math.random() * (9 - 1 + 1) ) + 1;
		Optional<Sala> sala = this.salaRepository.findById(Double.valueOf(inicio).longValue()).filter(s -> !s.isChegada());
		if (!sala.isPresent()) {
			sala = this.salaRepository.findFirstNaoChegada();
		}
		return sala.get();
	}
	
	@PostMapping("/save")
	public Sala salvarSala(@RequestBody Sala sala) {
		return this.salaService.salvarSala(sala);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Sala> editar(@PathVariable("id") long id,
			@RequestBody Sala sala) {
		Sala updated = new Sala();
		updated = this.salaService.editar(id, sala);
		return ResponseEntity.ok().body(updated);
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		this.salaService.deleteSala(id);
		return ResponseEntity.ok().build();
	}
}
