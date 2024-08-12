package com.turf.DTO;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDTO {
	@NotEmpty(message = "Email can't be blank")
	@Email(message = "Invalid email format")
	private String email;
	@NotEmpty(message = "Password can't be blank")
	@Length(min = 3,max=20,message = "Invalid password length")
	private String password;
	
	@NotEmpty(message = "Password can't be blank")
	@Length(min = 3,max=20,message = "Invalid password length")
	private String newpass1;
	@NotEmpty(message = "Password can't be blank")
	@Length(min = 3,max=20,message = "Invalid password length")
	private String newpass2;
	
}
