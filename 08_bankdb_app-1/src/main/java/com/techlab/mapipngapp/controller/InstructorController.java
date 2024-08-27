package com.techlab.mapipngapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.mapipngapp.dto.CourseDto;
import com.techlab.mapipngapp.dto.InstructorDto;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.StudentDto;
import com.techlab.mapipngapp.entity.Course;
import com.techlab.mapipngapp.entity.Instructor;
import com.techlab.mapipngapp.service.InstructorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {
	
	
	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructors")
	public InstructorDto addInstructor( @RequestBody @Valid  InstructorDto instructorDto) {
		return instructorService.addInstructor(instructorDto);
	}
	
	
	@PostMapping("/instructors/course")
	public ResponseEntity<Instructor>  allocateCourses(@RequestParam int instructorId,@RequestBody List<CourseDto> courses){
		
		return ResponseEntity.ok(instructorService.allocateCourses(instructorId,courses));
	}
	
	@GetMapping("/instructors")
	public ResponseEntity<Page<InstructorDto>> getAllInstructors(@RequestParam int pageNo,@RequestParam int pageSize) {
		// TODO Auto-generated constructor stub
		
		return ResponseEntity.ok(instructorService.getAllInstructorDtosPage(pageNo,pageSize));
	}
	
	@GetMapping("/instructors/{instructorId}")
	public ResponseEntity<InstructorDto> getInstructor(@PathVariable int instructorId){
		return ResponseEntity.ok(instructorService.getInstructorDto(instructorId));
	}
	
	@GetMapping("/instructors/courses/{instructorId}")
	public ResponseEntity<List<CourseDto>> getAllInstructorCourses(@PathVariable int instructorId) {
		// TODO Auto-generated constructor stub
		
		return ResponseEntity.ok(instructorService.getInstructorCourses(instructorId));
	}
	
	@GetMapping("/instructors/student/{instructorId}")
	public  ResponseEntity<PageResponse<StudentDto>> getAllStudentByInstrucor(@PathVariable int instructorId,@RequestParam int pageNo,@RequestParam int pageSize){
		return ResponseEntity.ok(instructorService.getAllStudentByInstructor(instructorId, pageNo, pageSize));
	}
	

}
