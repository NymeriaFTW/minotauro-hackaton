package com.ragatanga.iadtec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragatanga.iadtec.model.Estado;
import com.ragatanga.iadtec.model.Pais;
import com.ragatanga.iadtec.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	EstadoRepository estadoRepository;
	
	public List<Estado> findAll() {
		return this.estadoRepository.findAll();
	}
	
	public Estado salvarEstado(Estado estado) {
		return this.estadoRepository.save(estado);
	} 
	
	public void deleteEstado(Long idEstado) {
		this.estadoRepository.deleteById(idEstado);
	}
	
	public Estado editar(Long idEstado, Estado estado) {
		Estado updatedEstado = new Estado();
		updatedEstado = this.estadoRepository.save(estado);
		return updatedEstado;
	}
}
