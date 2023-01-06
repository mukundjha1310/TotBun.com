package com.totbun.DTOs;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegisterDTO {
	
	@NotNull(message = "Name field is mandatory.")
	@Size(min = 2, max = 15, message = "Name should not be less than size 2 and more than 15.")
	private String name;
	
	@NotNull(message = "Mobile number field is mandatory.")
	@Pattern(regexp = "\\d{10}", message = "Mobile number should not be less than or more than 10 digits.")
	private String mobile;
	
	@NotNull(message = "Email Id field is mandatory.")
	@Email(message = "Invalid Email address..!")
	private String emailId;
	
	@NotNull(message = "Password field is mandatory.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&])(?=\\S+$).{8,20}$", message = "Password must contain at least one digit [0-9], one lowercase character [a-z], one uppercase character [A-Z], one special character like [@#$%&] and a length of at least 8 characters, and a maximum of 20 characters with no space.")
	private String password;

	private Set<String> roles;
	
}
