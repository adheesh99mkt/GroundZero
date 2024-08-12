package com.turf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.BookingDTO;
import com.turf.DTO.BookingGetAllRespDTO;
import com.turf.DTO.UpdateBookingDTO;
import com.turf.custexception.NotFoundException;
import com.turf.entities.BookingEntity;
import com.turf.entities.GameEntity;
import com.turf.entities.Role;
import com.turf.entities.TurfEntity;
import com.turf.entities.UserEntity;
import com.turf.repositories.BookingRepository;
import com.turf.repositories.GameRepository;
import com.turf.repositories.TurfRepository;
import com.turf.repositories.UserRepository;
@Service
@Transactional
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TurfRepository turfRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse addBooking(@Valid Long playerId, BookingDTO dto) throws NotFoundException {
		UserEntity user=userRepository.findById(playerId).orElseThrow(()->new NotFoundException("player is not valid"));
		TurfEntity turf=turfRepository.findById(dto.getTurf_id()).orElseThrow(()->new NotFoundException("turf is not valid"));
		GameEntity game=gameRepository.findById(dto.getGame_id()).orElseThrow(()->new NotFoundException("game is not valid"));
		if(user.getRole()==Role.PLAYER) {
			if(dto.getNo_of_players()<=turf.getMax_capacity()) {
				if(game!=null&& turf.getGames().contains(game)) {
					BookingEntity book=modelMapper.map(dto, BookingEntity.class);
					book.setGame(game);
					book.setTurf(turf);
					bookingRepository.save(book);
					book.addPlayer(user);
					return new ApiResponse(book.getNo_of_players()+" players slot booked successfully");
				}
				else {
					return new ApiResponse(game.getGame_name()+" is not available in"+turf.getTurf_name()+" or there is no such game "+game.getGame_name());
				}
				
			}
			else {
				return new ApiResponse("Maximum turf capacity is "+turf.getMax_capacity()+""
						+ " ,but the number of players you given is "+dto.getNo_of_players());
			}
			
		}
		return new ApiResponse("Booking is only for Players,not for "+user.getRole());
	}

	@Override
	public ApiResponse joinBooking(@Valid Long playerId, @Valid Long bookingId,UpdateBookingDTO dto) throws NotFoundException {
		UserEntity user=userRepository.findById(playerId).orElseThrow(()->new NotFoundException("player is not valid"));
		BookingEntity booking=bookingRepository.findById(bookingId).orElseThrow(()->new NotFoundException("booking is not valid"));
		TurfEntity turf=turfRepository.findById(booking.getTurf().getId()).orElseThrow(()->new NotFoundException("player is not valid"));
		if(user!=null && booking!=null) {
			if(dto.getNo_of_players()<=turf.getMax_capacity()-booking.getNo_of_players()) {
				bookingRepository.updateBooking(bookingId,dto.getNo_of_players()+booking.getNo_of_players());
				booking.addPlayer(user);
				return new ApiResponse("Your booking for "+turf.getTurf_name()+" on "+booking.getDate()+" slot "+booking.getSlot()+" is done!");
			}
			else {
				return new ApiResponse("The remaining number of slot is "+(turf.getMax_capacity()-booking.getNo_of_players())+" ,So choose according to that!!");
			}
			
			
			
		}
		return new ApiResponse("Something went wrong!May be there is no such booking!");
	}

	@Override
	public List<BookingGetAllRespDTO> getAll() throws NotFoundException {
		List<BookingGetAllRespDTO> bookings=new ArrayList<BookingGetAllRespDTO>();
		List<BookingGetAllRespDTO> newbookings=bookingRepository.findAll() 
				.stream()
				.map(booking -> 
				modelMapper.map(booking,BookingGetAllRespDTO.class)) //Stream<dto>
				.collect(Collectors.toList());
		for (BookingGetAllRespDTO book : newbookings) {
			book.setGame_id(bookingRepository.findById(book.getId()).orElseThrow(()->new NotFoundException("Exception")).getGame().getId());
			book.setTurf_id(bookingRepository.findById(book.getId()).orElseThrow(()->new NotFoundException("Exception")).getTurf().getId());
			bookings.add(book);
		}
		return bookings;
		
		
		
		
	}

	@Override
	public List<BookingEntity> getBookingOfTurf(@Valid Long turfId) {
		return bookingRepository.findByTurfId(turfId) 
				.stream() 
				.map(booking -> 
				modelMapper.map(booking,BookingEntity.class)) //Stream<dto>
				.collect(Collectors.toList());
	}

	@Override
	public List<BookingEntity> getBookingOfPlayer(@Valid Long playerId) throws NotFoundException {
		UserEntity user = userRepository.findById(playerId).orElseThrow(()->new NotFoundException("no such user!"));
		
		List<BookingEntity> newbookings=new ArrayList<BookingEntity>();
		List<BookingEntity> bookings= bookingRepository.findAll() 
				.stream() 
				.map(booking -> 
				modelMapper.map(booking,BookingEntity.class)) //Stream<dto>
				.collect(Collectors.toList());
		for (BookingEntity book : bookings) {
			if(book.getPlayers().contains(user)) {
				newbookings.add(book);
			}
			
		}
		return newbookings;
		
		
	}
	


}
