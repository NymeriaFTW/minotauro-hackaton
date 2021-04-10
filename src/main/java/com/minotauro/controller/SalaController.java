package com.minotauro.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minotauro.model.Sala;
import com.minotauro.model.SalaSaida;
import com.minotauro.service.SalaService;

@RestController
@RequestMapping("api/sala")
@CrossOrigin(origins = "*" )
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	
	@GetMapping("/{tamanho}")
	public Sala iniciar(@PathVariable("tamanho") long tamanho) {		
		return salaService.iniciar(tamanho);
	}

	@GetMapping("/{sala}/{saida}/{tamanho}")
	public Sala salvarSala(@PathVariable("sala") Long salaId,
			@PathVariable("saida") String saida,
			@PathVariable("tamanho") Long tamanho) throws Exception {
		return this.salaService.getProximaSala(salaId, saida, tamanho);
	}
	
	@GetMapping
	public List<Sala> mapa() throws Exception {
		return this.salaService.getMapa();
	}
	
}
