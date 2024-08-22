package com.techlab.jpacrud.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentErrorRespone {
	
	private int statusCode;
	private String errorMessage;
	private long timeStamp;

}
