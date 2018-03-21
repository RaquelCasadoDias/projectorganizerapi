package com.examples.projectorganizerapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;



@Entity
@Table(name="client")
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long clientId;
	@Column()
	@NotBlank(message="Field clientName can't be blank")
	private String clientName;
	@Column
	@NotBlank(message="Field managerName can't be blank")
	private String managerName;
	@Column(unique=true)
	@Email
	@NotBlank(message="Field managerEmail can't be blank")
	private String managerEmail;
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	
	public Client(Long clientId, String clientName, String managerName, String managerEmail) {
		this.clientId = clientId;
		this.clientName = clientName;
		this.managerName = managerName;
		this.managerEmail = managerEmail;
	}
	
	public Client() {
		
	}
	
	
	
	

}
