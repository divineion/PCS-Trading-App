package com.pcs.tradingapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserDto {
	@NotBlank(message = "Username is mandatory") @Size(min=3, max=30) 
	private String username;
	
	@Size(min = 8, max = 30, message = "The password must contain between 8 and 30 characters")
	@Pattern(
	        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_]).{8,30}$",
	        message = "The password must contain at least one upper, one lower, one number and one special character"
	    )
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	@NotBlank(message = "FullName is mandatory") 
	private String fullname;
	
	@NotBlank(message = "Role is mandatory") 
	private String role;
	
	public CreateUserDto(){}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	};
}
