package com.techlab.mapipngapp.dto;

import java.util.List;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddresSchema {
	
	
	
	private String buidingName;
	
	private String city;
	
	private int pincode;

}
