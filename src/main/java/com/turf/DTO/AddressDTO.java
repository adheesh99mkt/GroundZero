package com.turf.DTO;




import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {
	@NotBlank(message = "address required")
	private String addrLine1;
	
	private String addrLine2;
	
	@NotBlank(message = "city required")
	private String city;
	
	@NotBlank(message = "zipcode required")
	private String zipCode;
	
	@NotBlank(message = "state required")
	private String state;
	
	@NotBlank(message = "country required")
	private String country;
	
}
