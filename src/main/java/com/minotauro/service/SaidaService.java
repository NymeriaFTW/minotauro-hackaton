package com.minotauro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minotauro.model.Saida;
import com.minotauro.repositories.SaidaRepository;

@Service
public class SaidaService {

	@Autowired
	SaidaRepository saidaRepository;
	
	public List<Saida> findAll() {
		return this.saidaRepository.findAll();
	}
	
	public Saida salvarSaida(Saida saida) {
		return this.saidaRepository.save(saida);
	} 
	
	public void deleteSaida(Long idSaida) {
		this.saidaRepository.deleteById(idSaida);
	}
	
	public Saida editar(Long idSaida, Saida saida) {
		Saida updatedEstado = new Saida();
		updatedEstado = this.saidaRepository.save(saida);
		return updatedEstado;
	}
}
