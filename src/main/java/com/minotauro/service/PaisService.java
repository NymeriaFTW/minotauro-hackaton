package com.minotauro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minotauro.model.Pais;
import com.minotauro.repositories.PaisRepository;

@Service
public class PaisService {
	
	@Autowired
	PaisRepository paisRepository;
	
	public Pais salvarPais(Pais pais) {
		return this.paisRepository.save(pais);
	} 
	
	public void deletePais(Long idPais) {
		this.paisRepository.deleteById(idPais);
	}
	
	public Pais editar(Long idPais, Pais pais) {
		Pais updatedPais = new Pais();
		updatedPais = this.paisRepository.save(pais);
		return updatedPais;
	}
	
	
	
}
