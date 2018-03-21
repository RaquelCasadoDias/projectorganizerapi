package com.examples.projectorganizerapi.service;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.projectorganizerapi.dao.CollaboratorDAO;
import com.examples.projectorganizerapi.dao.ProjectDAO;
import com.examples.projectorganizerapi.dto.ProjectDTO;
import com.examples.projectorganizerapi.model.Collaborator;
import com.examples.projectorganizerapi.model.Project;
import com.examples.projectorganizerapi.util.DozerHelper;

@Service
public class ProjectService {

	
	@Autowired
	ProjectDAO projectDAO;
	
	@Autowired
	CollaboratorDAO collaboratorDAO;
	
	Mapper mapper = new DozerBeanMapper();

	
	public Project createProject(ProjectDTO projectDTO) {
		Project project = mapper.map(projectDTO, Project.class);
		return projectDAO.save(project);
	}
	
	public List<ProjectDTO> getAllProjects(){
		List<Project> projectsModel = projectDAO.findAll();
		List<ProjectDTO> projects = DozerHelper.map(mapper, projectsModel, ProjectDTO.class);
		return projects;
	}
	
	public ProjectDTO getProjectById(Long projectId) {
		Project project = projectDAO.findOne(projectId);
		ProjectDTO projectDTO = mapper.map(project, ProjectDTO.class);
		if(projectDTO == null) {
			return null;
		}
		return projectDTO;
	}
	
	public Project getProjectByIdWithCollaborators(Long projectId) {
		Project project = projectDAO.findOne(projectId);
		if(project == null) {
			return null;
		}
		return project;
	}
	
	public Project updateProject(Long projectId, Project updatedProject) {
		Project project = projectDAO.findOne(projectId);
		if(project == null) {
			return null;
		}
		
		project.setProjectId(updatedProject.getProjectId());
		project.setProjectName(updatedProject.getProjectName());
		project.setStartDate(updatedProject.getStartDate());
		project.setFinishDate(updatedProject.getFinishDate());
		project.setClient_id(updatedProject.getClient_id());
		
		return projectDAO.save(project);
		
	}
	
	public Project deleteProject(Long projectId) {
		Project project = projectDAO.findOne(projectId);
		if(project == null) {
			return null;
		}
		projectDAO.delete(project);
		return project;
	}
	
	public Project addCollaborator(Long projectId, Long collaboratorId) {
		Project project = projectDAO.findOne(projectId);
		Collaborator collaborator = collaboratorDAO.findOne(collaboratorId);
		if(collaborator == null) {
			return null;
		}
		project.addCollaborator(collaborator);
		projectDAO.save(project);
		return project;
	}
	
	public Project removeCollaborator(Long projectId, Long collaboratorId) {
		Project project = projectDAO.findOne(projectId);
		Collaborator collaborator = collaboratorDAO.findOne(collaboratorId);
		List<Collaborator> projectCollaborators = project.getCollaborators();
		int projectCollaboratorsListLength = projectCollaborators.size();
		for (int i=0; i< projectCollaboratorsListLength;i++) {
			if(projectCollaborators.get(i).getCollaboratorId().equals(collaboratorId)){
				project.deleteCollaborator(collaborator);
				projectDAO.save(project);
				return project;
			}
		}
		return null;
	}
}
