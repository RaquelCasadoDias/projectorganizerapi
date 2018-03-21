package com.examples.projectorganizerapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examples.projectorganizerapi.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	List<Project> findByCollaborators_CollaboratorId(Long collaboratorId);	

}