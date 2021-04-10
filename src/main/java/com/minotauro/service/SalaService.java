package com.minotauro.service;

import java.util.ArrayList;
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
	
	public Sala iniciar(long tamanho) {
		populaBase(tamanho);
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

	private void populaBase(long tamanho) {
		List<Sala> salas = criaSalas(tamanho);
		criaSalaSaida(salas, tamanho);
	}

	private void criaSalaSaida(List<Sala> salas, long tamanho) {
		List<SalaSaida> salvar = new ArrayList<>();
		long id = 1;
		int quantidadePercorrida = 0;
		int linha = 1;
		for (Sala sala : salas) {
			quantidadePercorrida++;
			long numeroSala = Long.parseLong(sala.getNome().replace("Sala ", ""));
			if (numeroSala == 1) {
				salvar.add(new SalaSaida(id++, 1, sala.getId()));
				salvar.add(new SalaSaida(id++, 2, sala.getId()+1));
				salvar.add(new SalaSaida(id++, 4, sala.getId()));
				salvar.add(new SalaSaida(id++, 3, sala.getId()+tamanho));
			} else {
				if (quantidadePercorrida < (tamanho*linha) && linha != tamanho) {
					if ((quantidadePercorrida) % 2 != 0) {
						salvar.add(new SalaSaida(id++, 4, sala.getId()));
						salvar.add(new SalaSaida(id++, 3, sala.getId()+tamanho));
					}
					if ((quantidadePercorrida) % 2 == 0) {
						salvar.add(new SalaSaida(id++, 1, sala.getId()));
						salvar.add(new SalaSaida(id++, 2, sala.getId()+1));
					}
				}
			}
			if (quantidadePercorrida < (tamanho*linha) && linha == tamanho) {
				salvar.add(new SalaSaida(id++, 1, sala.getId()));
				salvar.add(new SalaSaida(id++, 2, sala.getId()+1));
			}
			if (quantidadePercorrida >= (tamanho*linha) && linha != tamanho) {
				linha++;
				if (quantidadePercorrida % 2 == 0) {
					salvar.add(new SalaSaida(id++, 4, sala.getId()));
					salvar.add(new SalaSaida(id++, 3, sala.getId()+tamanho));
				}
			}
		}
		this.salaSaidaRepository.saveAll(salvar);
	}

	private List<Sala> criaSalas(long tamanho) {
		this.salaRepository.deleteAll();
		List<Sala> salas = new ArrayList<>();
		long numero = 1;
		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < tamanho; j++) {
				salas.add(new Sala(numero, "Sala " + numero ));
				numero++;
			}	
		}
		double idRandom = Math.floor(Math.random() * ((tamanho*tamanho) - 1 + 1) ) + 1;
		salas.get(Double.valueOf(idRandom).intValue() - 1).setChegada(true);
		return this.salaRepository.saveAll(salas);
	}
	
}
