package com.ragatanga.iadtec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragatanga.iadtec.model.Cliente;
import com.ragatanga.iadtec.model.ClienteDTO;
import com.ragatanga.iadtec.repositories.ClienteRepository;
import com.ragatanga.iadtec.service.ClienteService;

@RestController
@RequestMapping("api/cliente")
@CrossOrigin(origins = "*" ) 
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping()
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	@PostMapping("/save")
	public Cliente saveClient(@RequestBody ClienteDTO clienteDTO) {
		return clienteRepository.save(clienteService.atribuiDTO(clienteDTO));
	}
	
}
