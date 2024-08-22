package com.techlab.jpacrud.service;

import org.springframework.data.domain.Page;

import com.techlab.jpacrud.dto.PageResponse;
import com.techlab.jpacrud.entity.Student;

public interface StudentService {

	PageResponse<Student> getAllStudent(int pageNo, int pageSize);

	String addUpdateStudent(Student student);

	String deleteStudent(int rollnumber);

	Student getStudentById(int rollnumber);
	
	Page<Student> findByName(String name, int pageNo, int pageSize);
}
