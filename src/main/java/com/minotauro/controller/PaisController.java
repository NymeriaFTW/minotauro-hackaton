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

import com.minotauro.model.Pais;
import com.minotauro.repositories.PaisRepository;
import com.minotauro.service.PaisService;

@RestController
@RequestMapping("api/pais")
@CrossOrigin(origins = "*" )
public class PaisController {
	
	@Autowired
	private PaisRepository paisRepository;
	
	@Autowired
	private PaisService paisService;
	
	@GetMapping()
	public ResponseEntity<List<Pais>> listarPaises() {
		List<Pais> paises = this.paisRepository.findAll();
		return ResponseEntity.ok(paises);
	}
	
	@PostMapping("/save")
	public Pais salvarPais(@RequestBody Pais pais) {
		return this.paisService.salvarPais(pais);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Pais> editar(@PathVariable("id") long id,
	                                        @RequestBody Pais pais) {
		Pais updated = new Pais();
		updated = this.paisService.editar(id, pais);
		return ResponseEntity.ok().body(updated);
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		this.paisService.deletePais(id);
		return ResponseEntity.ok().build();
	}
}
