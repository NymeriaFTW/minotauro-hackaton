package com.minotauro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minotauro.model.Saida;
import com.minotauro.model.Sala;
import com.minotauro.model.SalaSaida;
import com.minotauro.repositories.SaidaRepository;
import com.minotauro.repositories.SalaRepository;
import com.minotauro.repositories.SalaSaidaRepository;

@Service
public class SalaService {
	
	@Autowired
	SalaRepository salaRepository;
	
	@Autowired
	SaidaRepository saidaRepository;
	
	@Autowired
	SalaSaidaRepository salaSaidaRepository;
	
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
	
	public Sala iniciar() {
		double inicio = Math.floor(Math.random() * (9 - 1 + 1) ) + 1;
		Optional<Sala> sala = this.salaRepository.findById(Double.valueOf(inicio).longValue()).filter(s -> !s.isChegada());
		if (!sala.isPresent()) {
			sala = this.salaRepository.findFirstNaoChegada();
		}
		inicio = Math.floor(Math.random() * (4 - 1 + 1) ) + 1;
		Optional<Saida> saida = this.saidaRepository.findById(Double.valueOf(inicio).longValue());
		Sala salaInicial = sala.get();
		salaInicial.setVisaoAtual(saida.get().getNome());
		salaInicial.setSaidas(this.salaSaidaRepository.findBySalaId(salaInicial));
		return salaInicial;
	}
	
}
