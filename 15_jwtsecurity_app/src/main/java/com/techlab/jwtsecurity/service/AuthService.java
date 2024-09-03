package com.techlab.jwtsecurity.service;

import com.techlab.jwtsecurity.dto.LoginDto;
import com.techlab.jwtsecurity.dto.RegistrationDto;
import com.techlab.jwtsecurity.entity.User;

public interface AuthService {
	
	User register(RegistrationDto registrationDto);
	
	String login(LoginDto loginDto);

}
