package com.techlab.jwtsecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlab.jwtsecurity.dto.JwtTokenProvider;
import com.techlab.jwtsecurity.dto.LoginDto;
import com.techlab.jwtsecurity.dto.RegistrationDto;
import com.techlab.jwtsecurity.entity.Role;
import com.techlab.jwtsecurity.entity.User;
import com.techlab.jwtsecurity.exception.UserApiException;
import com.techlab.jwtsecurity.repository.RoleRepository;
import com.techlab.jwtsecurity.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Override
	public User register(RegistrationDto registrationDto) {
		// TODO Auto-generated method stub
		
		if(userRepository.existsByUserName(registrationDto.getUserName())) {
			throw new UserApiException(HttpStatus.BAD_REQUEST,"user already exists");
			
		}
		
		User user = new User();
		user.setUserName(registrationDto.getUserName());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		
		List<Role> roles = new ArrayList<>();
		Role userRole = roleRepository.findByRoleName(registrationDto.getRole()).orElseThrow(()->new RuntimeException("Invalid role"));
		roles.add(userRole);
		user.setRoles(roles);
		return userRepository.save(user);
	}

	@Override
	public String login(LoginDto loginDto) {
		// TODO Auto-generated method stub
	  try {
		  
		  Authentication authentication = authenticationManager.authenticate(
				   new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword())
				   );
		   
		   SecurityContextHolder.getContext().setAuthentication(authentication); 
		   String token = tokenProvider.generateToken(authentication);
				   
			return token;
		  
	  }catch (BadCredentialsException e) {
		// TODO: handle exception
		  throw new UserApiException(HttpStatus.NOT_FOUND,"user or password is incorrect");
	}
	}
	
	
	

}
