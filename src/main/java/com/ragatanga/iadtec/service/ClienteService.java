package com.ragatanga.iadtec.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragatanga.iadtec.model.Cliente;
import com.ragatanga.iadtec.model.ClienteDTO;
import com.ragatanga.iadtec.model.Estado;
import com.ragatanga.iadtec.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
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
	
	public Cliente editar(Long idCliente, ClienteDTO clienteDTO) {
		Cliente updatedCliente;
		clienteRepository.deleteById(idCliente);
		updatedCliente = this.clienteRepository.save(atribuiDTO(clienteDTO));
		return updatedCliente;
	}
	
	public void deleteCliente(Long idCliente) {
		this.clienteRepository.deleteById(idCliente);
	}
}
