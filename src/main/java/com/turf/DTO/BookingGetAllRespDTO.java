package com.turf.DTO;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.turf.entities.BookingSlot;
import com.turf.entities.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingGetAllRespDTO extends BaseDTO{
	//@NotBlank(message = "User Name required")
		private Long no_of_players;
		
		@NotNull(message = "Date of Birth required")@FutureOrPresent
		private LocalDate date;
		
		private BookingSlot slot;
		
		private BookingStatus status;
		
		//@NotBlank(message = "you have to select one game")
		//@JsonProperty(access = Access.READ_ONLY)// this property only used during de-ser.
		private Long game_id;
		
		//@NotBlank(message = " you have to select one turf")
		//@JsonProperty(access = Access.READ_ONLY)// this property only used during de-ser.
		private Long turf_id;
		
		//@NotBlank(message="You have to confirm the booking if you want slot")
		private boolean confirmation;
		
		
}
