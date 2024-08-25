package com.techlab.mapipngapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.mapipngapp.dto.CourseDto;
import com.techlab.mapipngapp.dto.InstructorDto;
import com.techlab.mapipngapp.entity.Instructor;
import com.techlab.mapipngapp.service.InstructorService;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {
	
	
	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructors")
	public Instructor addInstructor( @RequestBody  InstructorDto instructorDto) {
		return instructorService.addInstructor(instructorDto);
	}
	
	
	@PostMapping("/instructors/course")
	public ResponseEntity<Instructor>  allocateCourses(@RequestParam int instructorId,@RequestBody List<CourseDto> courses){
		
		return ResponseEntity.ok(instructorService.allocateCourses(instructorId,courses));
	}
	

}
