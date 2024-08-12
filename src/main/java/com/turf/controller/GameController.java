package com.turf.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.GameAddDTO;

import com.turf.DTO.TurfRegDTO;
import com.turf.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
	@Autowired
	private GameService gameService;
	
	
	@PostMapping("/{adminId}")
	public ResponseEntity<?> addGame(@PathVariable @Valid Long adminId,@RequestBody GameAddDTO dto){
		try {
			ApiResponse resp=gameService.addGame(adminId,dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		}
		catch(Exception e) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body(new ApiResponse(e.getMessage()));
		}
	}
}
