package com.techlab.mapipngapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.techlab.mapipngapp.dto.CustomerUserDto;
import com.techlab.mapipngapp.dto.JwtAuthResponse;
import com.techlab.mapipngapp.dto.LoginDto;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.RegisterDto;
import com.techlab.mapipngapp.dto.UserDto;
import com.techlab.mapipngapp.entity.User;
import com.techlab.mapipngapp.service.AuthService;
import com.techlab.mapipngapp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	//creating customer
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/users/register")
	public ResponseEntity<User> createUser(@RequestBody @Valid  RegisterDto user) {
		return ResponseEntity.ok(authService.register(user));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/users")
	public ResponseEntity<PageResponse<User>> getAllUsers(@Valid
            @RequestParam(required = false) Integer pageNo,
            @Valid
            @RequestParam(required = false) Integer pageSize
                                      ){
			  
     pageNo = pageNo == null ? 0 : pageNo; 
     pageSize = pageSize == null ? 10 : pageSize;
     return ResponseEntity.ok(userService.getAllUsers(pageNo, pageSize));
     }

	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/users/customer")
	public ResponseEntity<User> updateUserByCustomerId(@RequestParam int customerId ,  @RequestBody @Valid CustomerUserDto user) {
		return ResponseEntity.ok(userService.updateCustomerUser(customerId,user));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	@GetMapping("/users/customer")
	public ResponseEntity<UserDto> getUserByCustomer(@RequestParam int customerId) {
		return ResponseEntity.ok(userService.getUserByCustomerId(customerId));
	}
	
	
	@PostMapping("/user/login")
	public ResponseEntity<JwtAuthResponse> loginUser(@Valid @RequestBody LoginDto login){
		String token = authService.login(login);
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		
		return ResponseEntity.ok(jwtAuthResponse);
	}

}
