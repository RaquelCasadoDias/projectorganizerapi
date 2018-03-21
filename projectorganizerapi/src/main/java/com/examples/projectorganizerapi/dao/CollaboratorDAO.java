package com.examples.projectorganizerapi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.projectorganizerapi.model.Collaborator;
import com.examples.projectorganizerapi.repository.CollaboratorRepository;

@Service
public class CollaboratorDAO {
	
	@Autowired
	CollaboratorRepository collaboratorRepository;
	
	public Collaborator save(Collaborator collaborator) {
		return collaboratorRepository.save(collaborator);
	}
	
	public List<Collaborator> findAll(){
		return collaboratorRepository.findAll();
	}
	
	public Collaborator findOne(Long collaboratorId) {
		return collaboratorRepository.findOne(collaboratorId);
	}
	
	public Collaborator findOneByEmail(String email) {
		return collaboratorRepository.findOneByEmail(email);
	}
	
	public void delete(Collaborator collaborator) {
		collaboratorRepository.delete(collaborator);
	}

}
