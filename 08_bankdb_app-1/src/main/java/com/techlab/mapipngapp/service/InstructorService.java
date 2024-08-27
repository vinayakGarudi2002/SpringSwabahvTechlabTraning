package com.techlab.mapipngapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.techlab.mapipngapp.dto.CourseDto;
import com.techlab.mapipngapp.dto.InstructorDto;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.StudentDto;
import com.techlab.mapipngapp.entity.Instructor;

public interface InstructorService {

	InstructorDto addInstructor(InstructorDto instructor);

	Instructor allocateCourses(int instructorId, List<CourseDto> courses);
	
	List<CourseDto> getInstructorCourses(int instructorId);
	
	List<InstructorDto> getAllInstructorDtos();
	
	InstructorDto getInstructorDto(int instructorId);

	Page<InstructorDto> getAllInstructorDtosPage(int pageNo, int pageSize);
	
	
	PageResponse<StudentDto> getAllStudentByInstructor(int instructorId,int pageNo, int pageSize);

}
