package com.ragatanga.iadtec.controller;

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

import com.ragatanga.iadtec.model.Pais;
import com.ragatanga.iadtec.service.PaisService;

@RestController
@RequestMapping("api/pais")
@CrossOrigin(origins = "*" )
public class PaisController {
	
	@Autowired
	private PaisService paisService;
	
	@GetMapping()
	public ResponseEntity<List<Pais>> listarPaises() {
		List<Pais> paises = paisService.findAll();
		return ResponseEntity.ok(paises);
	}
	
	@PostMapping("/save")
	public Pais salvarPais(@RequestBody Pais pais) {
		return this.paisService.salvarPais(pais);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Pais> update(@PathVariable("id") long id,
	                                        @RequestBody Pais pais) {
		
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path ={"/id={id}"})
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		this.paisService.deletePais(id);
		return ResponseEntity.ok().build();
	}
}
