package com.turf.DTO;

import com.turf.entities.BookingStatus;
import com.turf.entities.TurfStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

		
public class TurfBookingConfirmationDTO {
	
		private Long booking_id;
		
		private BookingStatus status;
	

}
