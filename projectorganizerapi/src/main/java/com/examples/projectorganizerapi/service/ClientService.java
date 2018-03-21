package com.examples.projectorganizerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.projectorganizerapi.dao.ClientDAO;
import com.examples.projectorganizerapi.model.Client;

@Service
public class ClientService {
	
	@Autowired
	ClientDAO clientDAO;
	
	public Client createClient(Client client) {
		return clientDAO.save(client);
	}
	
	public List<Client> getAllClients(){
		return clientDAO.findAll();
	}
	
	public Client getClientById(Long clientId) {
		Client client = clientDAO.findOne(clientId);
		if(client==null) {
			return null;
		}
		return client;
	}
	
	public Client updateClient(Long clientId, Client inputedClient) {
		Client client = clientDAO.findOne(clientId);
		if(client == null) {
			return null;
		}
		
		client.setClientName(inputedClient.getClientName());
		client.setManagerName(inputedClient.getManagerName());
		client.setManagerEmail(inputedClient.getManagerEmail());
		
		return clientDAO.save(client);
	}
	
	public Client deleteClient(Long clientId) {
		Client client = clientDAO.findOne(clientId);
		if(client == null) {
			return null;
		}
		clientDAO.delete(client);
		return client;
	}

}
