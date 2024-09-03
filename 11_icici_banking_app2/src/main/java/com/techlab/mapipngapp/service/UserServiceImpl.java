package com.techlab.mapipngapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.common.methods.CreateResponse;
import com.techlab.mapipngapp.dto.CustomerUserDto;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.RegisterDto;
import com.techlab.mapipngapp.dto.UserDto;
import com.techlab.mapipngapp.entity.Customer;
import com.techlab.mapipngapp.entity.Profile;
import com.techlab.mapipngapp.entity.Role;
import com.techlab.mapipngapp.entity.User;
import com.techlab.mapipngapp.exception.UserApiException;
import com.techlab.mapipngapp.operation.CustomerOperation;
import com.techlab.mapipngapp.operation.UserOperation;
import com.techlab.mapipngapp.repository.ProfileRepository;
import com.techlab.mapipngapp.repository.RoleRepository;
import com.techlab.mapipngapp.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CustomerOperation customerOperation;
	
	@Autowired
	private CreateResponse<User> createResponse;
	
	@Autowired
	private UserOperation userOperation;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@Override
	public User createUser(RegisterDto regUser) {
		// TODO Auto-generated method stub
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
		user.setPassword(regUser.getPassword());
		User dbUser=userRepo.save(user);
		
		if(dbUser.getRoles().contains(roleRepository.findByRoleName("ROLE_CUSTOMER"))) {
		customerOperation.createCustomer(dbUser);
		}
		return dbUser;
	}

	@Override
	public PageResponse<User> getAllUsers(@Valid Integer pageNo, @Valid Integer pageSize) {
		// TODO Auto-generated method stub
		
		Pageable page = PageRequest.of(pageNo, pageSize); 
		Page <User> userPage= userRepo.findAll(page);
		
		return createResponse.createPageResponse(userPage);
	}

	@Override
	public User updateCustomerUser(int customerId, CustomerUserDto customerDto) {
		// TODO Auto-generated method stub
		Customer customer = customerOperation.isCustomerExist(customerId);
		
		User user = customer.getUser();
		
		
		 try {
			  
			  Authentication authentication = authenticationManager.authenticate(
					   new UsernamePasswordAuthenticationToken(user.getProfile().getEmailId(), customerDto.getPassword())
					   );
			   
			
		  }catch (BadCredentialsException e) {
			// TODO: handle exception
			  throw new UserApiException(HttpStatus.NOT_FOUND,"user or password is incorrect");
		}
		
		if(customerDto.getNewPassword()!=null) {
			user.setPassword(passwordEncoder.encode(customerDto.getNewPassword()));
		}
		

			Profile dbProfile=user.getProfile();
	
			if(customerDto.getFirstName()!=null) {
				dbProfile.setFirstName(customerDto.getFirstName());
			}
			
			if(customerDto.getLastName()!=null) {
				dbProfile.setLastName(customerDto.getLastName());
			}
			
			user.setProfile(dbProfile);
		
		
		return userRepo.save(user);
	}

	@Override
	public UserDto getUserByCustomerId(int customerId) {
        Customer customer = customerOperation.isCustomerExist(customerId);
		
		
		return userOperation.toUserDtoMapper(customer.getUser());
	}

	@Override
	public UserDto userLogin(String email, String password) {
		// TODO Auto-generated method stub
		if(profileRepository.findByEmailId(email)==null) {
			throw new RuntimeException("email or password is Incorect");
		}
		Profile profile = profileRepository.findByEmailId(email);
		
		User user = userRepo.findByProfile(profile);
		
		if(!user.getPassword().equals(password)) {
			throw new RuntimeException("email or password is Incorrect");
		}
		return userOperation.toUserDtoMapper(user);
	}
	
	
	
	
	
	 

}
