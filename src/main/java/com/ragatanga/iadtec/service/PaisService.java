package com.ragatanga.iadtec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragatanga.iadtec.model.Pais;
import com.ragatanga.iadtec.repositories.PaisRepository;

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
