package com.turf.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.turf.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRespDTO extends BaseDTO {
	
	@JsonProperty(access = Access.READ_ONLY)
	private String userName;
	@JsonProperty(access = Access.READ_ONLY)
	private String phoneNo;
	@JsonProperty(access = Access.READ_ONLY)
	private String email;
	@JsonProperty(access = Access.READ_ONLY)
	private Role role;
	
	
}
