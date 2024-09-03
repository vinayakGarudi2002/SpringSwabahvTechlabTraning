package com.techlab.mapipngapp.service;

import com.techlab.mapipngapp.dto.LoginDto;
import com.techlab.mapipngapp.dto.RegisterDto;
import com.techlab.mapipngapp.entity.User;

public interface AuthService {
	
	User register(RegisterDto registrationDto);
	
	String login(LoginDto loginDto);

}
