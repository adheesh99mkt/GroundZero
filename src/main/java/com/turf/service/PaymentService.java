package com.turf.service;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.PaymentAddDTO;
import com.turf.custexception.NotFoundException;

public interface PaymentService {

	ApiResponse addPayment(PaymentAddDTO dto)throws NotFoundException;

	//ApiResponse getAll()throws NotFoundException;

}
