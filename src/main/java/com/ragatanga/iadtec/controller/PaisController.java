package com.ragatanga.iadtec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragatanga.iadtec.model.Pais;
import com.ragatanga.iadtec.repositories.PaisRepository;

@RestController
@RequestMapping("/pais")
@CrossOrigin(origins = "*" )
public class PaisController {
	
	@Autowired
	private PaisRepository paisRepository;
	
	@GetMapping()
	public List<Pais> findAll() {
		return paisRepository.findAll();
	}
	
	@PostMapping("/save")
	public Pais savePais(@RequestBody Pais pais) {
		return paisRepository.save(pais);
	}
}
