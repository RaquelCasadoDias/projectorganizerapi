package com.examples.projectorganizerapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;



@Entity
@Table(name="collaborator")
public class Collaborator {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long collaboratorId;
	@NotBlank(message="Field name can't be blank")
	private String collaboratorName;
	@NotBlank(message="Field login can't be blank")
	@Column(unique=true)
	private String login;
	@Email (message= "Field email only accepts valid email formats")
	@NotBlank(message="Field email can't be blank")
	@Column(unique=true)
	private String email;
	@NotBlank(message="Field mobile can't be blank")
	@Pattern(regexp="^[0-9]+$", message = "Field mobile only accepts numbers" )
	private String mobile;
	
	public Long getCollaboratorId() {
		return collaboratorId;
	}
	public void setCollaboratorId(Long collaboratorId) {
		this.collaboratorId = collaboratorId;
	}
	public String getCollaboratorName() {
		return collaboratorName;
	}
	public void setCollaboratorName(String collaboratorName) {
		this.collaboratorName = collaboratorName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Collaborator(Long collaboratorId, String collaboratorName, String login, String email, String mobile) {
		super();
		this.collaboratorId = collaboratorId;
		this.collaboratorName = collaboratorName;
		this.login = login;
		this.email = email;
		this.mobile = mobile;
	}
	

	public Collaborator() {
		
	}
	
	
	

}
