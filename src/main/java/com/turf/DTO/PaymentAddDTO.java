package com.turf.DTO;


import javax.validation.constraints.NotNull;

import com.turf.entities.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAddDTO {
	@NotNull(message = "Enter the amount")
	private double amount;
	
	@NotNull(message = "Enter your account number")
	private Long player_accNo;
	
	@NotNull(message = "Enter admin account number")
	private Long admin_accNo;
	
	
	private PaymentStatus status;
	
	
	private Long player_id;
	
	
	private Long booking_id;
	
}
