package com.turf.DTO;

import com.turf.entities.TurfStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TurfConfirmationDTO {
		
	private Long turf_id;
	
	private TurfStatus status;
}
