package com.turf.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import com.turf.entities.GameEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class TurfRegDTO {
	@NotBlank(message = "Turf Name required")
	private String turf_name;
	
//	@Lob
//	private byte[] image;
	
	@NotBlank(message = "Price required")
	private Double price_hr;
	
	@NotBlank(message = "Capacity required")
	private Long max_capacity;
	
	
	@NotBlank(message = "games required")
	private List<GameDTO> games;
	
	@NotBlank(message = "Please provide owner id")
	@JsonProperty(access = Access.WRITE_ONLY)// this property only used during de-ser.
	private Long owner_id;
	
	@NotBlank(message = "Address of turf is mandatory")
	private AddressDTO address;
	
}
