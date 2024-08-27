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
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.StudentDto;
import com.techlab.mapipngapp.entity.Addres;
import com.techlab.mapipngapp.entity.Student;
import com.techlab.mapipngapp.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<PageResponse<StudentDto>> getAllStudents(@RequestParam int pageNo , @RequestParam int pageSize) {

	    return ResponseEntity.ok(studentService.getAllStudentDto(pageNo,pageSize));
	    
	    
	}
	
	@GetMapping("/students/adddres/{rollnumber}")
	public ResponseEntity<Addres> getStudentAddres(@PathVariable int rollnumber) {

	    return ResponseEntity.ok(studentService.getAddresDtoById(rollnumber));
	    
	    
	}
	
	@PostMapping("/students/adddres")
	public ResponseEntity<Addres> updateStudentAddres(@RequestParam int rollnumber, @RequestBody @Valid AddresSchema addres) {

	    return ResponseEntity.ok(studentService.updateAddresById(rollnumber, addres));
	    
	    
	}

	
	@PostMapping("/students")
	public Student addUpdateStudent(@RequestBody @Valid Student student) {
		return studentService.addUpdateStudent(student);
	}
	
//	@GetMapping("/students")
//	public ResponseEntity<PageResponse<StudentDto>> getAllStudents(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize){
//            return ResponseEntity.ok(studentService.getAllStudents(pageNo, pageSize));		
//	}
	
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<StudentDto> getStudentByRollnumber(@PathVariable("rollnumber") int rollnumber) {
	    System.out.println(rollnumber);
	    // Your logic here
	    return ResponseEntity.ok(studentService.getStudentByRollNumber(rollnumber));
	}
	
	@PostMapping("students/courses")
	public ResponseEntity<StudentDto> assignCoursesToStudent(@RequestParam("rollnumber") int rollnumber,@RequestBody List<Integer> courseIds){
		return ResponseEntity.ok(studentService.assignCourses(rollnumber, courseIds));
	}
	

}
