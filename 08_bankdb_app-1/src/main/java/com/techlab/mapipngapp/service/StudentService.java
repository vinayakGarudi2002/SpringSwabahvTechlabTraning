package com.techlab.mapipngapp.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.mapipngapp.dto.AddresDto;
import com.techlab.mapipngapp.dto.AddresSchema;
import com.techlab.mapipngapp.dto.StudentDto;
import com.techlab.mapipngapp.entity.Addres;
import com.techlab.mapipngapp.entity.Student;

public interface StudentService {
	
	Student addUpdateStudent(Student student);

	List<Student> getAllStudent();

	List<StudentDto> getAllStudentDto();

	Addres getAddresDtoById(int rollnumber);

	Addres updateAddresById(int rollnumber, AddresSchema addres);

}
