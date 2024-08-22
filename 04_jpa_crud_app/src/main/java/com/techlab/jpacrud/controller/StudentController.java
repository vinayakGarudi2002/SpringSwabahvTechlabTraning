package com.techlab.jpacrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.jpacrud.entity.Student;
import com.techlab.jpacrud.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String name) {
	    if (name != null && !name.isEmpty()) {
	        return ResponseEntity.ok(studentService.findByName(name));
	    }
	    return ResponseEntity.ok(studentService.getAllStudent());
	}

	
	@PostMapping("/students")
	public String addUpdateStudent(@RequestBody Student student) {
		return studentService.addUpdateStudent(student);
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
