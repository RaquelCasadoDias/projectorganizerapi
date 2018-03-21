package com.examples.projectorganizerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examples.projectorganizerapi.model.Collaborator;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>{
	
	Collaborator findOneByEmail(String email);

}
