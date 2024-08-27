package com.techlab.mapipngapp.service;

import java.util.List;

import com.techlab.mapipngapp.dto.CourseDto;
import com.techlab.mapipngapp.entity.Course;

public interface CourseService {
	
	Course addCourse(CourseDto corseDto);

	Course alloacteInstructorToCourse(int courseId, int instructorId);
	
	CourseDto assignStudentsToCourse(int courseId , List<Integer> rollnumbers);

}
