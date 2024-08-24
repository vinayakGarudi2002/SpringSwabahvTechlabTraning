package com.techlab.mapipngapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.mapipngapp.dto.AddresSchema;
import com.techlab.mapipngapp.dto.StudentDto;
import com.techlab.mapipngapp.entity.Addres;
import com.techlab.mapipngapp.entity.Student;
import com.techlab.mapipngapp.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> getAllStudents() {

	    return ResponseEntity.ok(studentService.getAllStudentDto());
	    
	    
	}
	
	@GetMapping("/students/adddres/{rollnumber}")
	public ResponseEntity<Addres> getStudentAddres(@PathVariable int rollnumber) {

	    return ResponseEntity.ok(studentService.getAddresDtoById(rollnumber));
	    
	    
	}
	
	@PostMapping("/students/adddres")
	public ResponseEntity<Addres> updateStudentAddres(@RequestParam int rollnumber, @RequestBody AddresSchema addres) {

	    return ResponseEntity.ok(studentService.updateAddresById(rollnumber, addres));
	    
	    
	}

	
	@PostMapping("/students")
	public Student addUpdateStudent(@RequestBody Student student) {
		return studentService.addUpdateStudent(student);
	}

}
