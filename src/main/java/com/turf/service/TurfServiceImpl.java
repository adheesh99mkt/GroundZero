package com.turf.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.GameDTO;
import com.turf.DTO.RemoveGameFromTurfDTO;
import com.turf.DTO.TurfRegDTO;
import com.turf.DTO.UpdateTurfDTO;
import com.turf.custexception.ApiException;
import com.turf.custexception.NotFoundException;
import com.turf.entities.AddressEntity;
import com.turf.entities.BookingStatus;
import com.turf.entities.GameEntity;
import com.turf.entities.Role;
import com.turf.entities.TurfEntity;
import com.turf.entities.TurfStatus;
import com.turf.entities.UserEntity;
import com.turf.repositories.AddressRepository;
import com.turf.repositories.GameRepository;
import com.turf.repositories.TurfRepository;
import com.turf.repositories.UserRepository;
@Service
@Transactional
public class TurfServiceImpl implements TurfService {
	
	
	@Autowired
	private TurfRepository turfRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse regTurf(TurfRegDTO dto) throws NotFoundException {
		UserEntity user=userRepository.findById(dto.getOwner_id()).orElseThrow(()->new NotFoundException("Owner is not valid"));
//		List<GameEntity> gamesdto=new ArrayList<GameEntity>();
//		for (GameDTO gameDTO : dto.getGames()) {
//			games(modelMapper.map(gameDTO, GameEntity.class));
//		}
		AddressEntity address= addressRepository.save(modelMapper.map(dto.getAddress(), AddressEntity.class));
		TurfEntity turf=new TurfEntity();
		
			
			turf.setMyAddress(address);
			turf.setOwner(user);
			turf.setPrice_hr(dto.getPrice_hr());
			turf.setTurf_name(dto.getTurf_name());
			turf.setMax_capacity(dto.getMax_capacity());
			turf.setStatus(TurfStatus.PENDING);
			if(user.getRole()==Role.OWNER) {
				for (GameDTO gameDto : dto.getGames()) {
					GameEntity g=gameRepository.findById(gameDto.getGame_id()).orElseThrow(()->new NotFoundException("no such game"));
					turf.addGame(g);
				}
				turfRepository.save(turf);
				return new ApiResponse("Turf "+turf.getTurf_name()+" is registered by you ("+user.getUserName()+")");
				
			}
			throw new ApiException("This functionality is only for owners!");
		
//		}
//		throw new ApiException("This turf name is already existing");
		
		
	}

	@Override
	public ApiResponse updateTurf(@Valid Long turfId, UpdateTurfDTO dto) throws NotFoundException {
		TurfEntity turf=turfRepository.findById(turfId).orElseThrow(()->new NotFoundException("Turf is not valid"));
		if(dto.getOwner_id().equals(turf.getOwner().getId())) {
			turf.setMax_capacity(dto.getMax_capacity());
			turf.setPrice_hr(dto.getPrice_hr());
			for (GameDTO gameDto : dto.getGames()) {
				GameEntity g=gameRepository.findById(gameDto.getGame_id()).orElseThrow(()->new NotFoundException("no such game"));
				turf.addGame(g);
			}
			turfRepository.save(turf);
			return new ApiResponse("Turf "+turf.getTurf_name()+" updated!");
		}
		throw new ApiException("This turf is not under your ownership");
	}

	@Override
	public ApiResponse removeGamesFromTurf(@Valid Long turfId, RemoveGameFromTurfDTO dto) throws NotFoundException {
		TurfEntity turf=turfRepository.findById(turfId).orElseThrow(()->new NotFoundException("Turf is not valid"));
		if(dto.getOwner_id().equals(turf.getOwner().getId())) {
			for (GameDTO gameDto : dto.getGames()) {
				GameEntity g=gameRepository.findById(gameDto.getGame_id()).orElseThrow(()->new NotFoundException("no such game"));
				turf.removeGame(g);
			}
			turfRepository.save(turf);
			return new ApiResponse("Turf "+turf.getTurf_name()+" updated!");
			
		}
		throw new ApiException("This turf is not under your ownership");
	}

}
