package com.turf.service;

import javax.validation.Valid;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.GameAddDTO;
import com.turf.DTO.GameDTO;
import com.turf.custexception.NotFoundException;

public interface GameService {

	ApiResponse addGame(@Valid Long adminId, GameAddDTO dto) throws NotFoundException;

}
