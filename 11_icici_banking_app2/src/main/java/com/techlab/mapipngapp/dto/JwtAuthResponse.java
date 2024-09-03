package com.techlab.mapipngapp.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Component
public class JwtAuthResponse {
	
	private String accessToken;
	private String tokenType ="Bearer";

}
