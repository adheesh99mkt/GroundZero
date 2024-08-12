package com.turf.DTO;



import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.turf.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDTO {
	
	//@JsonProperty(access = Access.READ_ONLY) // this property only used during ser.
//	private Long id;
	@NotBlank(message = "User Name required")
	private String userName;
	
	@Email(message = "Invalid Email!!!")
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)// this property only used during de-ser.
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
	private String password;
	
	
	@NotBlank(message = "Phone Number required")
	private String phoneNo;
	
	
	public UserUpdateDTO(String userName,
			String email, String password, String phoneNo) {
		super();
		
		this.userName = userName;
		this.email = email;
		this.password = password;		
		this.phoneNo=phoneNo;
		
	}
	
}


