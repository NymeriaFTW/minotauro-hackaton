package com.minotauro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minotauro.model.Sala;
import com.minotauro.repositories.SalaRepository;

@Service
public class SalaService {
	
	@Autowired
	SalaRepository salaRepository;
	
	public Sala salvarSala(Sala pais) {
		return this.salaRepository.save(pais);
	} 
	
	public void deleteSala(Long idSala) {
		this.salaRepository.deleteById(idSala);
	}
	
	public Sala editar(Long idPais, Sala sala) {
		Sala updatedPais = new Sala();
		updatedPais = this.salaRepository.save(sala);
		return updatedPais;
	}
	
	
	
}
