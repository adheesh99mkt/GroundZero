package com.turf.DTO;

import java.time.LocalDate;

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
public class UpdateBookingDTO {
	@NotBlank(message = "User Name required")
	private Long no_of_players;
	
}
