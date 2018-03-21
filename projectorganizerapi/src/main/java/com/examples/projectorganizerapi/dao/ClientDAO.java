package com.examples.projectorganizerapi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.projectorganizerapi.model.Client;
import com.examples.projectorganizerapi.repository.ClientRepository;

@Service
public class ClientDAO {
	
	@Autowired
	ClientRepository clientRepository;
	
	public Client save(Client client) {
		return clientRepository.save(client);
	}
	
	public List<Client> findAll(){
		return clientRepository.findAll();
	}
	
	public Client findOne(Long clientId) {
		return clientRepository.findOne(clientId);
	}
	
	public void delete(Client client) {
		clientRepository.delete(client);
	}
	
	

}
