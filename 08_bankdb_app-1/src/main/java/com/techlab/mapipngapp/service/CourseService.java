package com.techlab.mapipngapp.service;

import com.techlab.mapipngapp.dto.CourseDto;
import com.techlab.mapipngapp.entity.Course;

public interface CourseService {
	
	Course addCourse(CourseDto corseDto);

	Course alloacteInstructorToCourse(int courseId, int instructorId);

}
