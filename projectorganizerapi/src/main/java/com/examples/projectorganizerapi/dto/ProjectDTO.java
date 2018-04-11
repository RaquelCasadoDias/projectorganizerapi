package com.examples.projectorganizerapi.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ProjectDTO {
	
//	@NotBlank(message="Field projectId can't be blank")
	@NotNull(message="Field projectId can't be blank")
	private String projectId;
	@NotBlank(message="Field projectName can't be blank")
	private String projectName;
	@NotBlank(message="Field projectStartDate can't be blank")
	private String startDate;
	@NotBlank(message="Field finishDate can't be blank")
	private String finishDate;
//	@NotBlank(message="Field client_id can't be blank")
	@NotNull(message="Field clientId can't be blank")
	private long client_id;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
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
	public ProjectDTO() {
		super();
	}
	
	

}
