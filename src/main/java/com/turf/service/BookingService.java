package com.turf.service;

import java.util.List;

import javax.validation.Valid;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.BookingDTO;
import com.turf.DTO.BookingGetAllRespDTO;
import com.turf.DTO.TurfBookingConfirmationDTO;
import com.turf.DTO.UpdateBookingDTO;
import com.turf.custexception.NotFoundException;
import com.turf.entities.BookingEntity;

public interface BookingService {

	ApiResponse addBooking(@Valid Long playerId, BookingDTO dto) throws NotFoundException;

	ApiResponse joinBooking(@Valid Long playerId, @Valid Long bookingId, UpdateBookingDTO dto)throws NotFoundException;

	List<BookingGetAllRespDTO> getAllBydate() throws NotFoundException;

	List<BookingEntity> getBookingOfTurf(@Valid Long turfId);

	List<BookingEntity> getBookingOfPlayer(@Valid Long playerId) throws NotFoundException;

	ApiResponse confirmTurfBooking(@Valid Long adminId, TurfBookingConfirmationDTO dto)throws NotFoundException;

	ApiResponse cancelBooking(@Valid Long userId, @Valid Long bookingId)throws NotFoundException;

	List<BookingGetAllRespDTO> getAll()throws NotFoundException;


}
