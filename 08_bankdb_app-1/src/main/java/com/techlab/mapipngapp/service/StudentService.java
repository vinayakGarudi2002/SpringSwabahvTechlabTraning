package com.techlab.mapipngapp.service;

import java.util.List;

import com.techlab.mapipngapp.dto.AddresSchema;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.StudentDto;
import com.techlab.mapipngapp.entity.Addres;
import com.techlab.mapipngapp.entity.Student;

public interface StudentService {
	
	Student addUpdateStudent(Student student);

	List<Student> getAllStudent();

	PageResponse<StudentDto> getAllStudentDto(int pageNo,int pageSize);

	Addres getAddresDtoById(int rollnumber);

	Addres updateAddresById(int rollnumber, AddresSchema addres);
	
PageResponse<StudentDto> getAllStudents(int paheNo,int pageSize);
	
	StudentDto getStudentByRollNumber(int rollnumber);
	
	StudentDto assignCourses(int rollnumber,List<Integer> courseIds);

}
