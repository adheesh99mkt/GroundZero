package com.turf.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
public class UpdateTurfDTO {
	
	private Double price_hr;
	
	
	private Long max_capacity;
	
	
	
	private List<GameDTO> games;
	
	@JsonProperty(access = Access.WRITE_ONLY)// this property only used during de-ser.
	private Long owner_id;
	
}
