package com.techlab.jpacrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.jpacrud.entity.Student;
import com.techlab.jpacrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRep;

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return studentRep.findAll();
	}
	
	@Override
	public String addUpdateStudent(Student student) {
		
		studentRep.save(student);
		return "Succesfully updated";
	}
	
	@Override
	public String deleteStudent(int rollnumber) {
		studentRep.deleteById(rollnumber);
		return "Succesfully deleted";
	}
	
	@Override
	public Student getStudentById(int rollnumber) {
	return studentRep.findById(rollnumber).get();
		
	}

	@Override
	public List<Student> findByName(String name) {
		
		return studentRep.findByName(name);
	}

}
