package com.turf.service;

import java.util.List;

import javax.validation.Valid;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.ChangePasswordDTO;
import com.turf.DTO.SignInRequest;

import com.turf.DTO.SignUp;
import com.turf.DTO.UserRespDTO;
import com.turf.DTO.UserUpdateDTO;
import com.turf.custexception.NotFoundException;
import com.turf.entities.UserEntity;

public interface UserService {

	ApiResponse createUser(SignUp user);

	//SignInResponse login(AuthDTO dto)throws NotFoundException;

	List<UserEntity> getAll();

	UserRespDTO getById(Long userId)throws NotFoundException;

	UserRespDTO logIn(SignInRequest dto)throws NotFoundException;

	UserRespDTO updateUser(@Valid Long userId, UserUpdateDTO dto) throws NotFoundException;

	ApiResponse changePass(ChangePasswordDTO dto);

	ApiResponse deleteUserByAdmin(@Valid Long adminId, @Valid Long userId)throws NotFoundException;

	ApiResponse deleteUser(@Valid Long userId)throws NotFoundException;
	
}
