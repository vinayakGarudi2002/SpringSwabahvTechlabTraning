package com.techlab.jwtsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.jwtsecurity.dto.JwtAuthResponse;
import com.techlab.jwtsecurity.dto.LoginDto;
import com.techlab.jwtsecurity.dto.RegistrationDto;
import com.techlab.jwtsecurity.entity.User;
import com.techlab.jwtsecurity.service.AuthService;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegistrationDto registrationDto){
		return ResponseEntity.ok(authService.register(registrationDto));
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
		String token = authService.login(loginDto);
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		
		return ResponseEntity.ok(jwtAuthResponse);
	}

}
