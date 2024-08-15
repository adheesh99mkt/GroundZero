package com.turf.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.PaymentAddDTO;
import com.turf.custexception.NotFoundException;
import com.turf.entities.PaymentEntity;
import com.turf.entities.UserEntity;
import com.turf.repositories.PaymentRepository;
import com.turf.repositories.UserRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public ApiResponse addPayment(PaymentAddDTO dto) throws NotFoundException {
		PaymentEntity payment=modelMapper.map(dto, PaymentEntity.class);
		
		UserEntity player=userRepository.findById(dto.getPlayer_id()).orElseThrow(()->new NotFoundException("Not a valid user"));
		paymentRepository.save(payment);
		
		return new ApiResponse("Hey "+player.getUserName()+" Your Payment added successfully");
	}

//	@Override
//	public List<PaymentRespDTO> getAll() throws NotFoundException {
//		
//	}

}
