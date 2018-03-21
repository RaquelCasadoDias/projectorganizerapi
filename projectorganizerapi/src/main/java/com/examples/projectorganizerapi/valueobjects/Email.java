package com.examples.projectorganizerapi.valueobjects;

import javax.validation.constraints.Pattern;

public class Email {
	
//	@EmailValidation(ErrorMessage = "The Email Address already exists")
	@Pattern(regexp="^[a-z0-9_\\+-]+(\\.[a-z0-9_\\+-]+)*@[a-z0-9-]+(\\.[a-z0-9]+)*\\.([a-z]{2,4})$" , message = "Invalid email format." )
	public String Email;


	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	} 

}
