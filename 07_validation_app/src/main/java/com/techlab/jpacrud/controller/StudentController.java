package com.techlab.jpacrud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.techlab.jpacrud.dto.PageResponse;
import com.techlab.jpacrud.entity.Student;
import com.techlab.jpacrud.errors.StudentErrorRespone;
import com.techlab.jpacrud.exceptions.StudentNotFoundException;
import com.techlab.jpacrud.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<PageResponse<Student>> getAllStudents(@RequestParam(required = false) String name,@RequestParam(required = false) int pageNo,@RequestParam(required = false) int pageSize) {
//	    if (name != null && !name.isEmpty()) {
//	    	
//	        return ResponseEntity.ok(studentService.findByName(name,pageNo,pageSize));
//	    }
	    return ResponseEntity.ok(studentService.getAllStudent(pageNo,pageSize));
	}

	
	 @PostMapping("/students")
	    public ResponseEntity<String> addUpdateStudent(@Valid @RequestBody Student student) {
	        studentService.addUpdateStudent(student);
	        return ResponseEntity.ok("Student added/updated successfully");
	    }

	   
	
	@DeleteMapping("/students/{rollnumber}")
	public String deleteStudentById(@PathVariable int rollnumber) {
		return studentService.deleteStudent(rollnumber);
	}
	@GetMapping("/students/{rollnumber}")
	public Student getStudentById(@PathVariable int rollnumber) {
		return studentService.getStudentById(rollnumber);
	}
	
	
	
}
