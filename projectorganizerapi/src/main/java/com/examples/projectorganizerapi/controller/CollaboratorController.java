package com.examples.projectorganizerapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examples.projectorganizerapi.dto.CollaboratorDTO;
import com.examples.projectorganizerapi.model.Collaborator;
import com.examples.projectorganizerapi.service.CollaboratorService;


@RestController
@RequestMapping
public class CollaboratorController {
	
	@Autowired
	CollaboratorService collaboratorService;
	
	@PostMapping("/collaborators")
	public ResponseEntity<Collaborator> createCollaborator(@Valid @RequestBody Collaborator collaborator){
		Collaborator CollaboratorWithDuplicatedEmail = collaboratorService.createCollaorator(collaborator);
		URI location = URI.create("http://localhost:8080/collaborators/" + collaborator.getCollaboratorId());
		
		if(CollaboratorWithDuplicatedEmail == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.created(location).body(collaborator);
		}
		
	}
	
	@GetMapping("/collaborators")
	public List<Collaborator> getAllCollaborators(){
		return collaboratorService.getAllCollaborators();
	}
	

	@GetMapping("/collaborators/{collaboratorId}")
	public ResponseEntity<Collaborator> getCollaboratorById(@PathVariable(value="collaboratorId")Long collaboratorId){
		Collaborator collaborator = collaboratorService.getCollaboratorById(collaboratorId);
		if(collaborator == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(collaborator);
	}
	
	@GetMapping("/collaboratorswithdetails/{collaboratorId}")
	public ResponseEntity<CollaboratorDTO> getCollaboratorByIdWithDetails(@PathVariable(value="collaboratorId")Long collaboratorId){
		CollaboratorDTO collaboratorDTO = collaboratorService.getCollaboratorByIdWithDetails(collaboratorId);
		if(collaboratorDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(collaboratorDTO);
	}
	
	@PutMapping("/collaborators/{collaboratorId}")
	public ResponseEntity<Collaborator> updateCollaborator(@PathVariable(value="collaboratorId")
	Long collaboratorId, @Valid @RequestBody Collaborator updatedCollaborator){
		Collaborator collaborator = collaboratorService.updateCollaborator(collaboratorId, updatedCollaborator);
		if(collaborator == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/collaborators/{collaboratorId}")
	public ResponseEntity<Collaborator> deleteCollaborator(@PathVariable(value="collaboratorId") 
	Long collaboratorId){
		Collaborator collaborator = collaboratorService.deleteCollaborator(collaboratorId);
		if(collaborator == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}


}
