package com.examples.projectorganizerapi.service;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.projectorganizerapi.dao.CollaboratorDAO;
import com.examples.projectorganizerapi.dao.ProjectDAO;
import com.examples.projectorganizerapi.dto.CollaboratorDTO;
import com.examples.projectorganizerapi.dto.ProjectDTO;
import com.examples.projectorganizerapi.model.Collaborator;
import com.examples.projectorganizerapi.model.Project;
import com.examples.projectorganizerapi.util.DozerHelper;


@Service
public class CollaboratorService {
	
	Mapper mapper = new DozerBeanMapper();
	
	
	
	
	@Autowired
	CollaboratorDAO collaboratorDAO;
	
	@Autowired
	ProjectDAO projectDAO;
	
	public Collaborator createCollaorator(Collaborator collaborator) {
		Collaborator collaboratorWithDuplicatedEmail = collaboratorDAO.findOneByEmail(collaborator.getEmail());
		if(collaboratorWithDuplicatedEmail == null) {
			return collaboratorDAO.save(collaborator);
		}
		return null;
	}
	
	public List<Collaborator> getAllCollaborators(){
		return collaboratorDAO.findAll();
	}
	

	public Collaborator getCollaboratorById(Long collaboratorId) {
		Collaborator collaborator = collaboratorDAO.findOne(collaboratorId);
		if(collaborator == null) {
			return null;
		}
		return collaborator;
	}
	
	public CollaboratorDTO getCollaboratorByIdWithDetails(Long collaboratorId) {
		Collaborator collaborator = collaboratorDAO.findOne(collaboratorId);
		List<Project> projects = projectDAO.findAllByCollaboratorId(collaboratorId);
		
		if(collaborator == null) {
			return null;
		}
		CollaboratorDTO collaboratorDTO = mapper.map(collaborator, CollaboratorDTO.class);
				
		List<ProjectDTO> projectsDTO = DozerHelper.map(mapper, projects, ProjectDTO.class);
		collaboratorDTO.projects = projectsDTO;
		return collaboratorDTO;
	}
	
	
	public Collaborator updateCollaborator(Long collaboratorId, Collaborator updatedCollaborator) {
		Collaborator collaborator = collaboratorDAO.findOne(collaboratorId);
		if(collaborator == null) {
			return null;
		}
		
		collaborator.setCollaboratorName(updatedCollaborator.getCollaboratorName());
		collaborator.setLogin(updatedCollaborator.getLogin());
		collaborator.setEmail(updatedCollaborator.getEmail());
		collaborator.setMobile(updatedCollaborator.getMobile());
		
		return collaboratorDAO.save(collaborator);
	}
	
	public Collaborator deleteCollaborator(Long collaboratorId) {
		Collaborator collaborator = collaboratorDAO.findOne(collaboratorId);
		if(collaborator == null) {
			return null;
		}
		collaboratorDAO.delete(collaborator);
		return collaborator;
	}

}
