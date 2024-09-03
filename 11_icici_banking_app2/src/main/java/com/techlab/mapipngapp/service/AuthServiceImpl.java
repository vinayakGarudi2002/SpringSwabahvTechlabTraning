package com.techlab.mapipngapp.service;

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

import com.techlab.mapipngapp.dto.LoginDto;
import com.techlab.mapipngapp.dto.RegisterDto;
import com.techlab.mapipngapp.entity.Profile;
import com.techlab.mapipngapp.entity.Role;
import com.techlab.mapipngapp.entity.User;
import com.techlab.mapipngapp.exception.UserApiException;
import com.techlab.mapipngapp.operation.CustomerOperation;
import com.techlab.mapipngapp.operation.UserOperation;
import com.techlab.mapipngapp.repository.RoleRepository;
import com.techlab.mapipngapp.repository.UserRepository;
import com.techlab.mapipngapp.security.JwtTokenProvider;

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
	
	@Autowired
	private CustomerOperation customerOperation;
	
	@Autowired
	private UserOperation userOperation;
	@Override
	public User register(RegisterDto regUser) {
		
		userOperation.isEmailExist(regUser.getEmailId());
		User user = new User();
		Role role = roleRepository.findByRoleName(regUser.getRole());
//		System.out.println(role.getRoleName());
		if(role==null) {
			throw new RuntimeException("Given role not Exist");
		}
		
		Profile profile = new Profile();
		profile.setEmailId(regUser.getEmailId());
		profile.setFirstName(regUser.getFirstName());
		profile.setLastName(regUser.getLastName());
		user.setProfile(profile);
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(regUser.getPassword()));
		User dbUser=userRepository.save(user);
		
		if(dbUser.getRoles().contains(roleRepository.findByRoleName("ROLE_CUSTOMER"))) {
		customerOperation.createCustomer(dbUser);
		}
		return dbUser;
	}

	@Override
	public String login(LoginDto loginDto) {
		// TODO Auto-generated method stub
	  try {
		  
		  Authentication authentication = authenticationManager.authenticate(
				   new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
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
