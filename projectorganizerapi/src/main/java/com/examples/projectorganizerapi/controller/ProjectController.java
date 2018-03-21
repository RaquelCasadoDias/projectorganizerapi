package com.examples.projectorganizerapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examples.projectorganizerapi.dto.ProjectDTO;
import com.examples.projectorganizerapi.model.Project;
import com.examples.projectorganizerapi.service.ProjectService;

@RestController
@RequestMapping
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@PostMapping("/projects")
	public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody  ProjectDTO projectDTO){
		projectService.createProject(projectDTO);
		URI location = URI.create("http://localhost:8080/projects/" + projectDTO.getProjectId());
		return ResponseEntity.created(location ).body(projectDTO);
	}
	
	@GetMapping("/projects")
	public List<ProjectDTO> getAllprojects(){
		return projectService.getAllProjects();
	}

	
	@GetMapping("/projects/{projectId}")
	public ResponseEntity<ProjectDTO> getprojectById(@PathVariable(value="projectId")Long projectId){
		ProjectDTO projectDTO = projectService.getProjectById(projectId);
		if(projectDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(projectDTO);
	}
	
	@GetMapping("/projectswithdetails/{projectId}")
	public ResponseEntity<Project> getprojectByIdWithCollaborators(@PathVariable(value="projectId")Long projectId){
		Project project = projectService.getProjectByIdWithCollaborators(projectId);
		if(project == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(project);
	}
	
	@PutMapping("/projects/{projectId}")
	public ResponseEntity<Project> updateProject(@PathVariable(value="projectId")
	Long projectId, @Valid @RequestBody Project updatedProject){
		Project project = projectService.updateProject(projectId, updatedProject);
		if(project == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/projects/{projectId}")
	public ResponseEntity<Project> deleteProject(@PathVariable(value="projectId") 
	Long projectId) {
		Project project = projectService.deleteProject(projectId);
		if(project == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	
	@PatchMapping("/projects/{projectId}/collaborators/+{collaboratorId}")
	public ResponseEntity<Project> addCollaborator(@PathVariable(value="projectId") Long projectId, @PathVariable(value="collaboratorId") Long collaboratorId) {
		Project project = projectService.addCollaborator(projectId, collaboratorId);
		if(project == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/projects/{projectId}/collaborators/-{collaboratorId}")
	public ResponseEntity<Project> removeCollaborator(@PathVariable(value="projectId") Long projectId, @PathVariable(value="collaboratorId") Long collaboratorId){
		Project project = projectService.removeCollaborator(projectId, collaboratorId);
		if(project == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}
