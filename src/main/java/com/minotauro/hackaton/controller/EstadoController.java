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

import com.minotauro.model.Estado;
import com.minotauro.model.Pais;
import com.minotauro.repositories.EstadoRepository;
import com.minotauro.service.EstadoService;

@RestController
@RequestMapping("api/estado")
@CrossOrigin(origins = "*" )
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping()
	public List<Estado> findAll() {
		return estadoRepository.findAll();
	}
	
	@PostMapping("/save")
	public Estado saveEstado(@RequestBody Estado estado) {
		return estadoRepository.save(estado);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Estado> editar(@PathVariable("id") long id,
	                                        @RequestBody Estado estado) {
		Estado updated = new Estado();
		updated = this.estadoService.editar(id, estado);
		return ResponseEntity.ok().body(updated);
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		this.estadoService.deleteEstado(id);
		return ResponseEntity.ok().build();
	}
	
	
}
