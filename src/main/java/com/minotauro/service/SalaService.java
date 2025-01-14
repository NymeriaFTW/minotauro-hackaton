package com.minotauro.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		salaInicial.setTamanho(tamanho);
		return salaInicial;
	}

	private void populaBase(long tamanho) {
		this.salaSaidaRepository.deleteAll();
		this.salaRepository.deleteAll();
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
					//if ((quantidadePercorrida) % 2 != 0) {
						salvar.add(new SalaSaida(id++, 4, sala.getId()));
						salvar.add(new SalaSaida(id++, 3, sala.getId()+tamanho));
					//}
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

	public Sala getProximaSala(Long salaId, String saida, Long tamanho) throws Exception {
		long proximaSala = 0;
		switch (saida) {
			case "N":
				proximaSala = salaId - tamanho;
				break;
			case "O":
				proximaSala = salaId - 1;
				break;
			case "S":
				proximaSala = salaId + tamanho;
				break;
			default:
				proximaSala = salaId + 1;
				break;
		}
		Optional<Sala> optionalSala = this.salaRepository.findById(proximaSala);
		if (!optionalSala.isPresent()) {
			throw new Exception("Sala não existe.");
		}
		Sala sala = optionalSala.get();
		sala.setVisaoAtual(saida);
		sala.setSaidas(this.salaSaidaRepository.findBySalaId(sala));
		sala.setTamanho(tamanho);
		return sala;
	}

	public List<Sala> getMapa() {
		List<SalaSaida> salasSaida = this.salaSaidaRepository.findAll();
		List<Sala> salas = new ArrayList<>();
		salasSaida.forEach(ss -> {
			Sala sala = ss.getSala();
			sala.addSaida(ss.getSaida().getNome());
			if (salas.contains(sala)) {				
				int indexOf = salas.indexOf(sala);
				Sala sala2 = salas.get(indexOf);
				if (!sala2.getSaidas().isEmpty()) {
					if (!sala2.getSaidas().contains(ss.getSaida().getNome())) {
						sala2.addSaida(ss.getSaida().getNome());
					}
				} else {
					sala2.addSaida(ss.getSaida().getNome());
				}
			} else {
				salas.add(sala);
			}
		});
		return salas.stream().sorted(Comparator.comparingLong(Sala::getId)).collect(Collectors.toList());
	}
	
}
