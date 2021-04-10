package com.minotauro.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minotauro.repositories.SalaSaidaRepository;

@Service
public class SalaSaidaService {

	@Autowired
	SalaSaidaRepository salaSaidaRepository;
	
	/**
	 * Conversão de uma string no formato "dd/MM/yyyy" para o formato Date.
	 * @param dataNasc
	 * @return
	 */
	public Date converteDataNasc(String dataNasc) {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date dataParse = formatoData.parse(dataNasc);
			Date dataNascimento = new Date(dataParse.getTime());
			return dataNascimento;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	/**
//	 * Atribuição dos valores do SalaSaidaDTO a um cliente.
//	 * @param clienteDTO
//	 * @return
//	 */
//	public SalaSaida atribuiDTO(SalaSaidaDTO clienteDTO) {
//		SalaSaida cliente = new SalaSaida();
//		cliente.setId(clienteDTO.getId());
//		cliente.setNome(clienteDTO.getNome());
//		cliente.setCpf(clienteDTO.getCpf());
//		cliente.setDataNascimento(converteDataNasc(clienteDTO.getDataNascimento()));
//		cliente.setEmail(clienteDTO.getEmail());
//		cliente.setSituacao(clienteDTO.getSituacao());
//		cliente.setEstado(clienteDTO.getEstado());		
//		return cliente;
//	}
	
//	public SalaSaida editar(Long idSalaSaida, SalaSaidaDTO clienteDTO) {
//		SalaSaida updatedSalaSaida;
//		clienteRepository.deleteById(idSalaSaida);
//		updatedSalaSaida = this.clienteRepository.save(atribuiDTO(clienteDTO));
//		return updatedSalaSaida;
//	}
	
	public void deleteSalaSaida(Long idSalaSaida) {
		this.salaSaidaRepository.deleteById(idSalaSaida);
	}
}
