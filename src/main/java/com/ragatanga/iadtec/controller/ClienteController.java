package com.ragatanga.iadtec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragatanga.iadtec.model.Cliente;
import com.ragatanga.iadtec.model.ClienteDTO;
import com.ragatanga.iadtec.model.Estado;
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
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Cliente> editar(@PathVariable("id") long id,
	                                        @RequestBody ClienteDTO clienteDTO) {
		Cliente updated = new Cliente();
		updated = this.clienteService.editar(id, clienteDTO);
		return ResponseEntity.ok().body(updated);
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		this.clienteService.deleteCliente(id);
		return ResponseEntity.ok().build();
	}
	
}
