package com.techlab.jpacrud.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.techlab.jpacrud.errors.StudentErrorRespone;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<StudentErrorRespone> handleStudentNotFoundException(StudentNotFoundException exception){
		
		
		StudentErrorRespone studentErrorResponse =new StudentErrorRespone();
		studentErrorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		studentErrorResponse.setErrorMessage(exception.getMessage());
		studentErrorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<StudentErrorRespone>(studentErrorResponse,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<StudentErrorRespone> handleStudentNotFoundException(MethodArgumentTypeMismatchException exception){
		
		
		StudentErrorRespone studentErrorResponse =new StudentErrorRespone();
		studentErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		studentErrorResponse.setErrorMessage("roll number need to be in integer");
		studentErrorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<StudentErrorRespone>(studentErrorResponse,HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
	
}
