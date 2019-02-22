package com.ragatanga.iadtec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ragatanga.iadtec.model.Estado;
import com.ragatanga.iadtec.repositories.EstadoRepository;

public class EstadoService {

	@Autowired
	EstadoRepository estadoRepository;
	
	public List<Estado> findAll() {
		return this.estadoRepository.findAll();
	}
	
	public Estado salvarEstado(Estado estado) {
		return this.estadoRepository.save(estado);
	} 
	
	public void deletePais(Long idEstado) {
		this.estadoRepository.deleteById(idEstado);
	}
}
