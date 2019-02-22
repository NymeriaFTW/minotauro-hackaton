package com.ragatanga.iadtec.service;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.ragatanga.iadtec.model.Cliente;
import com.ragatanga.iadtec.model.ClienteDTO;

@Service
public class ClienteService {

	
	
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
	
	/**
	 * Atribuição dos valores do ClienteDTO a um cliente.
	 * @param clienteDTO
	 * @return
	 */
	public Cliente atribuiDTO(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setId(clienteDTO.getId());
		cliente.setNome(clienteDTO.getNome());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setDataNascimento(converteDataNasc(clienteDTO.getDataNascimento()));
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setSituacao(clienteDTO.getSituacao());
		cliente.setEstado(clienteDTO.getEstado());		
		return cliente;
	}
}
