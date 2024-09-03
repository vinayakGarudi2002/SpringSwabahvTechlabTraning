package com.techlab.mapipngapp.operation;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.techlab.mapipngapp.dto.UserDto;
import com.techlab.mapipngapp.entity.Customer;
import com.techlab.mapipngapp.entity.Profile;
import com.techlab.mapipngapp.entity.User;
import com.techlab.mapipngapp.exception.UserApiException;
import com.techlab.mapipngapp.repository.CustomerRepository;
import com.techlab.mapipngapp.repository.ProfileRepository;
import com.techlab.mapipngapp.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserOperation {
	
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public User isUserExist(int userId) {
		
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent()) {
			throw new RuntimeException("User Not Exist with Id: "+userId);
		}
		
		return user.get();
		
	}
	
	
	public UserDto toUserDtoMapper(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setRole(user.getRoles());
		userDto.setProfile(user.getProfile());
		
		return userDto;
	}
	
	public boolean isEmailExist(String email) {
		if(profileRepository.findByEmailId(email)!=null) {
			throw new UserApiException(HttpStatus.BAD_REQUEST,"user already exists");
		}
		
		return false;
	}
	
	public String getCurrentUsername() {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if (principal instanceof UserDetails) {
	        return ((UserDetails) principal).getUsername();
	    } else {
	        return principal.toString();
	    }
	}
	
	public User getCurrentUser() {
	    
	    	Profile profile = profileRepository.findByEmailId(getCurrentUsername());
	    	
	    	User user = userRepository.findByProfile(profile);
	        return user;
	   
	}
	
	public Customer getCurrentCustomer() {
		return customerRepository.findByUser(getCurrentUser());
	}

}
