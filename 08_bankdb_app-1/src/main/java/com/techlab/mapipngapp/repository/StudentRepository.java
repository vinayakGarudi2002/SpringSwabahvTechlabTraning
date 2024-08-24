package com.techlab.mapipngapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.mapipngapp.dto.AddresDto;
import com.techlab.mapipngapp.dto.StudentDto;
import com.techlab.mapipngapp.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	 List<StudentDto> findAllProjectedBy();
	 
	
}
