package com.turf.DTO;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.turf.entities.BookingSlot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class GameAddDTO {
	@NotBlank(message = "Game Name required")
	private String game_name;
	
	@NotBlank(message = "Description about the game is required")
	private String description;
}
