package com.turf.service;

import javax.validation.Valid;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.RemoveGameFromTurfDTO;
import com.turf.DTO.TurfConfirmationDTO;
import com.turf.DTO.TurfRegDTO;
import com.turf.DTO.UpdateTurfDTO;
import com.turf.custexception.NotFoundException;

public interface TurfService {

	ApiResponse regTurf( TurfRegDTO dto) throws NotFoundException;

	ApiResponse updateTurf(@Valid Long turfId, UpdateTurfDTO dto) throws NotFoundException;

	ApiResponse removeGamesFromTurf(@Valid Long turfId, RemoveGameFromTurfDTO dto)throws NotFoundException;

	ApiResponse confirmTurfReg(@Valid Long adminId, @Valid TurfConfirmationDTO dto)throws NotFoundException;

}
