package com.examples.projectorganizerapi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.projectorganizerapi.model.Collaborator;
import com.examples.projectorganizerapi.model.Project;
import com.examples.projectorganizerapi.repository.CollaboratorRepository;
import com.examples.projectorganizerapi.repository.ProjectRepository;

@Service
public class ProjectDAO{
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	CollaboratorRepository collaboratorRepository;
	
	public Project save(Project project) {
		return projectRepository.save(project);
	}
	
	public List<Project> findAll(){
		return projectRepository.findAll();
	}
	
	public List<Project> findAllByCollaboratorId(Long collaboratorId){
		return projectRepository.findByCollaborators_CollaboratorId(collaboratorId);
	}
	
	public Project findOne(String projectId) {
		return projectRepository.findOne(projectId);
	}
	
	public void delete(Project project) {
		projectRepository.delete(project);
	}
	
	public void addCollaborator(String projectId, Long collaboratorId) {
		Project project = projectRepository.findOne(projectId);
		Collaborator collaborator = collaboratorRepository.findOne(collaboratorId);
		project.addCollaborator(collaborator);
		projectRepository.save(project);
		
	}
	
	public void removeCollaborator(String projectId, Long collaboratorId) {
		Project project = projectRepository.findOne(projectId);
		Collaborator collaborator = collaboratorRepository.findOne(collaboratorId);
		project.deleteCollaborator(collaborator);
		projectRepository.save(project);
		
	}


}
