package com.techlab.jwtsecurity.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class RegistrationDto {
	
	private String userName;
	private String password;
	private String role;

}
