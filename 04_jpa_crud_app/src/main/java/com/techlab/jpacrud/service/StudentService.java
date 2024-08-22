package com.techlab.jpacrud.service;

import java.util.List;

import com.techlab.jpacrud.entity.Student;

public interface StudentService {

	List<Student> getAllStudent();

	String addUpdateStudent(Student student);

	String deleteStudent(int rollnumber);

	Student getStudentById(int rollnumber);
	
	List<Student> findByName(String name);
}
