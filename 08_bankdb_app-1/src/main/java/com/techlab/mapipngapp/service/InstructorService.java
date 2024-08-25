package com.techlab.mapipngapp.service;

import java.util.List;

import com.techlab.mapipngapp.dto.CourseDto;
import com.techlab.mapipngapp.dto.InstructorDto;
import com.techlab.mapipngapp.entity.Instructor;

public interface InstructorService {

	Instructor addInstructor(InstructorDto instructor);

	Instructor allocateCourses(int instructorId, List<CourseDto> courses);

}
