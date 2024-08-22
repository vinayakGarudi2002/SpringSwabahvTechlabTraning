package com.techlab.jpacrud.exceptions;

public class StudentNotFoundException extends RuntimeException{

	
	public String getMessage() {
		return "Student with given roll number is not present";
	}
}
