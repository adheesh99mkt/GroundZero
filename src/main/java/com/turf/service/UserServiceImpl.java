
package com.turf.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turf.DTO.ApiResponse;
import com.turf.DTO.ChangePasswordDTO;
import com.turf.DTO.SignInRequest;
import com.turf.DTO.SignUp;
import com.turf.DTO.UserRespDTO;
import com.turf.DTO.UserUpdateDTO;
import com.turf.custexception.ApiException;
import com.turf.custexception.NotFoundException;
import com.turf.entities.BookingEntity;
import com.turf.entities.Role;
import com.turf.entities.UserEntity;
import com.turf.repositories.BookingRepository;
import com.turf.repositories.UserRepository;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BookingRepository bookingRepository;
	
//	@Autowired
//	private PasswordEncoder encoder;

	@Override
	public ApiResponse createUser(SignUp user) {
		// TODO Auto-generated method stub
		UserEntity usr=modelMapper.map(user, UserEntity.class);
		if(userRepository.existsByEmail(usr.getEmail())) {
			throw new ApiException("Email already present");
		}
//		user.setPassword(encoder.encode(user.getPassword()));//pwd : encrypted using SHA
		userRepository.save(usr);
		return new ApiResponse(usr.getUserName()+ "as"+ usr.getRole()+" Registered Successfully!");
	}

//	@Override
//	public SignInResponse authenticateUser(SignInRequest dto) throws NotFoundException{
//		UserEntity user=userRepository.findByEmail(dto.getEmail())
//				.orElseThrow(()->new NotFoundException("user not found"));
//		if(user!=null) {
//			if (encoder.matches(dto.getPassword(), user.getPassword())) {
//				return modelMapper.map(user, SignInResponse.class);
//            }
//		}
//		throw new NotFoundException("User not found!");
//		
//	}

	@Override
	public List<UserEntity> getAll() {
		return userRepository.findAll() //List<Category>
				.stream() //Stream<Category>
				.map(users -> 
				modelMapper.map(users,UserEntity.class)) //Stream<dto>
				.collect(Collectors.toList());
	}

	@Override
	public UserRespDTO getById(Long userId) throws NotFoundException {
		
		Optional<UserEntity> optional = userRepository.findById(userId);
		UserEntity user = optional.orElseThrow(() -> 
		new NotFoundException("Invalid User ID!!!!"));
		return modelMapper.map(user, UserRespDTO.class);
	}

	@Override
	public UserRespDTO logIn(SignInRequest dto) throws NotFoundException {
		UserEntity user=userRepository.findByEmail(dto.getEmail())
				.orElseThrow(()->new NotFoundException("user not found"));
		if(user!= null)
		{
			user=userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
			return modelMapper.map(user, UserRespDTO.class);
		}
		throw new NotFoundException("User is not valid");
		
	}

	@Override
	public UserRespDTO updateUser(@Valid Long userId, UserUpdateDTO dto) throws NotFoundException {
		
		Optional<UserEntity> optional = userRepository.findById(userId);
		UserEntity user = optional.orElseThrow(() -> 
		new NotFoundException("Invalid User ID!!!!"));
		
		if(user!=null) {
			user.setUserName(dto.getUserName());
			user.setEmail(dto.getEmail());
			user.setPassword(dto.getPassword());
			user.setPhoneNo(dto.getPhoneNo());
			userRepository.save(user);
		}
		return modelMapper.map(user, UserRespDTO.class);
	}

	@Override
	public ApiResponse changePass(ChangePasswordDTO dto) {
		UserEntity user=userRepository
				.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
		if(user!=null) {
			if( dto.getNewpass1().equals(dto.getNewpass2())){
				user.setPassword(dto.getNewpass1());
				userRepository.save(user);
				return new ApiResponse(user.getUserName()+"'s Password changed successfully!");
			}
			else {
				return new ApiResponse("Type the password exactly same!");
			}
			
		}
		return new ApiResponse("please check your email and old password!");
	}

	@Override
	public ApiResponse deleteUserByAdmin(@Valid Long adminId, @Valid Long userId) throws NotFoundException {
		Optional<UserEntity> useropt = userRepository.findById(userId);
		UserEntity user = useropt.orElseThrow(() -> 
		new NotFoundException("Invalid User ID!!!!"));
		Optional<UserEntity> adminopt = userRepository.findById(userId);
		UserEntity admin = adminopt.orElseThrow(() -> 
		new NotFoundException("Invalid User ID!!!!"));
		if(admin.getRole()==Role.ADMIN) {
			List<BookingEntity> bookings=bookingRepository.findAll();
			for (BookingEntity book : bookings) {
				if(book.getPlayers().contains(user)) {
					int no=0;
					for (UserEntity us : book.getPlayers()) {
						no++;
						
					}
					if(no>1) {
						book.removePlayer(user);
						bookingRepository.save(book);
						//return new ApiResponse("Hey! "+user.getUserName()+ ",your booking for "+book.getTurf().getTurf_name()+" is cancelled");
					}
					else {
						bookingRepository.delete(book);
						//return new ApiResponse("Hey! "+user.getUserName()+ ",your booking for "+book.getTurf().getTurf_name()+" is cancelled");
					}
				
				}
			}
			userRepository.delete(user);
			return new ApiResponse("The user "+user.getUserName()+" deleted by Admin");
		}
		throw new ApiException("Unauthorised functionality!");
	}

	@Override
	public ApiResponse deleteUser(@Valid Long userId) throws NotFoundException {
		Optional<UserEntity> useropt = userRepository.findById(userId);
		UserEntity user = useropt.orElseThrow(() -> 
		new NotFoundException("Invalid User ID!!!!"));
		List<BookingEntity> bookings=bookingRepository.findAll();
		for (BookingEntity book : bookings) {
			if(book.getPlayers().contains(user)) {
				int no=0;
				for (UserEntity us : book.getPlayers()) {
					no++;
					
				}
				if(no>1) {
					book.removePlayer(user);
					bookingRepository.save(book);
					//return new ApiResponse("Hey! "+user.getUserName()+ ",your booking for "+book.getTurf().getTurf_name()+" is cancelled");
				}
				else {
					bookingRepository.delete(book);
					//return new ApiResponse("Hey! "+user.getUserName()+ ",your booking for "+book.getTurf().getTurf_name()+" is cancelled");
				}
			
			}
		}
		
		userRepository.delete(user);
		return new ApiResponse("Hey "+user.getUserName()+", Your Account deleted successfully!");
	}

}
