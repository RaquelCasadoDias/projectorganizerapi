package com.examples.projectorganizerapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.examples.projectorganizerapi.model.Client;
import com.examples.projectorganizerapi.service.ClientService;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@PostMapping("/clients")
	public ResponseEntity<Client> createClient(@Valid @RequestBody  Client client){
		clientService.createClient(client);
		URI location = URI.create("http://localhost:8080/clients/" + client.getClientId());
		return ResponseEntity.created(location ).body(client);
	}
	
	@GetMapping("/clients")
	public List<Client> getAllClients(){
		return clientService.getAllClients();
	}
	
	@GetMapping("/clients/{clientId}")
	public ResponseEntity<Client> getClientById(@PathVariable(value="clientId")Long clientId){
		Client client = clientService.getClientById(clientId);
		if(client == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(client);
	}
	
	@PutMapping("/clients/{clientId}")
	public ResponseEntity<Client> updateClient(@PathVariable(value="clientId")
	Long clientId, @Valid @RequestBody Client updatedClient){
		Client client = clientService.updateClient(clientId, updatedClient);
		if(client == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/clients/{clientId}")
	public ResponseEntity<Client> deleteClient(@PathVariable(value="clientId") 
	Long clientId){
		Client client = clientService.deleteClient(clientId);
		if(client == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}
