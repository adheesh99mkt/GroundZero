package com.turf.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.GameAddDTO;
import com.turf.DTO.PaymentAddDTO;
import com.turf.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	
	@PostMapping
	public ResponseEntity<?> addGame(@RequestBody PaymentAddDTO dto){
		try {
			ApiResponse resp=paymentService.addPayment(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		}
		catch(Exception e) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body(new ApiResponse(e.getMessage()));
		}
	}
//	@GetMapping
//	public ResponseEntity<?> getAllPayment(){
//		try {
//			
//			return ResponseEntity.ok(paymentService.getAll());
//		}
//		catch(Exception e) {
//			return ResponseEntity.
//					status(HttpStatus.NOT_FOUND).
//					body(new ApiResponse(e.getMessage()));
//		}
//	}
	
}
