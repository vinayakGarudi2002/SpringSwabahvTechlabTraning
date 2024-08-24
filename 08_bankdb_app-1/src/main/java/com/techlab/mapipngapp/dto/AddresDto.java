package com.techlab.mapipngapp.dto;

import jakarta.persistence.Column;

public interface AddresDto {
	 int getAddresId();
	
	 String getBuidingName();
	
	 String getCity();
	
	 int getPincode();
}
