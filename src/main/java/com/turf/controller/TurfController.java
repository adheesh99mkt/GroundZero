package com.turf.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.RemoveGameFromTurfDTO;
import com.turf.DTO.TurfConfirmationDTO;
import com.turf.DTO.TurfRegDTO;
import com.turf.DTO.UpdateTurfDTO;
import com.turf.service.TurfService;

@RestController
@RequestMapping("/turf")
public class TurfController {
	@Autowired
	private TurfService turfService;
	
	@PostMapping
	public ResponseEntity<?> registerTurf(@RequestBody TurfRegDTO dto){
		try {
			ApiResponse resp=turfService.regTurf(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		}
		catch(Exception e) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PutMapping("/{turfId}")
	public ResponseEntity<?> updateTurf(@PathVariable @Valid Long turfId,@RequestBody UpdateTurfDTO dto){
		try {
			ApiResponse resp=turfService.updateTurf(turfId,dto);
			return ResponseEntity.ok(resp);
		}
		catch(Exception e) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body(new ApiResponse(e.getMessage()));
		}
	}
	@PutMapping("/removeGames/{turfId}")
	public ResponseEntity<?> removeGamesFromTurf(@PathVariable @Valid Long turfId,@RequestBody RemoveGameFromTurfDTO dto){
		try {
			ApiResponse resp=turfService.removeGamesFromTurf(turfId,dto);
			return ResponseEntity.ok(resp);
		}
		catch(Exception e) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body(new ApiResponse(e.getMessage()));
		}
	}
	@PutMapping("/turfReg/confirm/{adminId}")
	public ResponseEntity<?> confirmTurfReg(@PathVariable @Valid Long adminId,@RequestBody TurfConfirmationDTO dto){
		try {
			ApiResponse resp=turfService.confirmTurfReg(adminId,dto);
			return ResponseEntity.ok(resp);
		}
		catch(Exception e) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body(new ApiResponse(e.getMessage()));
		}
	}
	
	//delete turf
	@DeleteMapping("/{adminId}/{turfId}")
	public ResponseEntity<?> deleteTurf(@PathVariable @Valid Long adminId,@PathVariable @Valid Long turfId ){
		try {
			ApiResponse resp=turfService.deleteTurf(adminId,turfId);
			return ResponseEntity.ok(resp);
		}
		catch(Exception e) {
			return ResponseEntity.
					status(HttpStatus.NOT_FOUND).
					body(new ApiResponse(e.getMessage()));
		}
	}
	
}
