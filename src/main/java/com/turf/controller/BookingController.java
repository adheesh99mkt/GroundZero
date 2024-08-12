package com.turf.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.BookingDTO;
import com.turf.DTO.UpdateBookingDTO;
import com.turf.entities.UserEntity;
import com.turf.service.BookingService;
import com.turf.service.UserService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	
	@PostMapping("/{playerId}")
	public ResponseEntity<?> bookSlot(@PathVariable @Valid Long playerId,@RequestBody BookingDTO dto){
		try {
			ApiResponse resp=bookingService.addBooking(playerId,dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		}
		catch(Exception e) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PutMapping("/{bookingId}/{playerId}")
	public ResponseEntity<?> joinToBooking(@PathVariable @Valid Long playerId,@PathVariable @Valid Long bookingId,@RequestBody UpdateBookingDTO dto){
		try {
			ApiResponse resp=bookingService.joinBooking(playerId,bookingId,dto);
			return ResponseEntity.ok(resp);
		}
		catch(Exception e) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllBooking(){
		try {
			return ResponseEntity.ok(bookingService.getAll());
		}
		catch(Exception e) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/turf/{turfId}")
	public ResponseEntity<?> getBookingsOfTurf(@PathVariable @Valid Long turfId){
		try {
			return ResponseEntity.ok(bookingService.getBookingOfTurf(turfId));
		}
		catch(Exception e) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body(new ApiResponse(e.getMessage()));
		}
	}
}

