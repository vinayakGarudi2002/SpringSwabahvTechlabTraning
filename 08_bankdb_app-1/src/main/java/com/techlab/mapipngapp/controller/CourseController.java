package com.techlab.mapipngapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.mapipngapp.dto.CourseDto;
import com.techlab.mapipngapp.entity.Course;
import com.techlab.mapipngapp.service.CourseService;

@RestController
@RequestMapping("/studentapp")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/courses")
	public ResponseEntity<Course> addNewCourse(@RequestBody CourseDto courseDto){
		
		return ResponseEntity.ok(courseService.addCourse(courseDto));
	}
	
	@PutMapping("/courses/instructors")
	public ResponseEntity<Course> alloacteInstructorToCourse(@RequestParam int courseId,@RequestBody int instructorId){
		
		return ResponseEntity.ok(courseService.alloacteInstructorToCourse(courseId,instructorId));
	}
	
	
	
	

}
