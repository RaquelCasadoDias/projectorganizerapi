package com.examples.projectorganizerapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="project")
public class Project {
	
	@Id
	@Column(unique=true)
//	@NotBlank(message="Field projectId can't be blank")
	@NotNull(message="Field projectId can't be blank")
	private long projectId;
	@Column
	@NotBlank(message="Field projectName can't be blank")
	private String projectName;
	@Column
	@NotBlank(message="Field startDate can't be blank")
	private String startDate;
	@Column
	@NotBlank(message="Field finishDate can't be blank")
	private String finishDate;
	@Column
//	@NotBlank(message="Field clientId can't be blank")
	@NotNull(message="Field clientId can't be blank")
	private long client_id;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "project_collaborator", joinColumns = 
    @JoinColumn(name = "projectId", referencedColumnName ="projectId")
 , inverseJoinColumns =  
    @JoinColumn(name = "collaboratorId", referencedColumnName ="collaboratorId") 
	)

	private List<Collaborator> collaborators;
	
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	public long getClient_id() {
		return client_id;
	}
	public void setClient_id(long client_id) {
		this.client_id = client_id;
	}
	
	public List<Collaborator> getCollaborators() {
		return collaborators;
	}
	public void setCollaborators(List<Collaborator> collaborators) {
		this.collaborators = collaborators;
	}
	
	public Project(long projectId, String projectName, String startDate, String finishDate,
			long client_id) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.client_id = client_id;
	}
	
	public Project() {
		
	}
	
	
	public void addCollaborator(Collaborator collaborator) {
		collaborators.add(collaborator);
	}
	
	public void deleteCollaborator(Collaborator collaborator) {
		collaborators.remove(collaborator);
	}
}
