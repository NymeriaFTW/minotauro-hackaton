package com.ragatanga.iadtec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragatanga.iadtec.model.Estado;
import com.ragatanga.iadtec.repositories.EstadoRepository;

@RestController
@RequestMapping("api/estado")
@CrossOrigin(origins = "*" )
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping()
	public List<Estado> findAll() {
		return estadoRepository.findAll();
	}
	
	@PostMapping("/save")
	public Estado saveEstado(@RequestBody Estado estado) {
		return estadoRepository.save(estado);
	}
}
