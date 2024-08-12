package com.turf.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.GameAddDTO;
import com.turf.DTO.GameDTO;
import com.turf.custexception.ApiException;
import com.turf.custexception.NotFoundException;
import com.turf.entities.GameEntity;
import com.turf.entities.Role;
import com.turf.entities.UserEntity;
import com.turf.repositories.GameRepository;
import com.turf.repositories.UserRepository;

@Service
@Transactional
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse addGame(@Valid Long adminId, GameAddDTO dto) throws NotFoundException {
		UserEntity admin=userRepository.findById(adminId).orElseThrow(()->new NotFoundException("No such admin"));
		if(admin.getRole()==Role.ADMIN) {
			gameRepository.save(modelMapper.map(dto, GameEntity.class));
			return new ApiResponse("Game "+dto.getGame_name()+" is added");
		}
		throw new ApiException("Only admin can add games!");
	}

}
