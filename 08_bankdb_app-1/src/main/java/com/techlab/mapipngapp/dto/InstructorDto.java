package com.techlab.mapipngapp.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class InstructorDto {
	
	private int instructorId;
	
	private String name;
	
	private String email;
	
	private String qualification;
}
