package com.techlab.jpacrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlab.jpacrud.dto.PageResponse;
import com.techlab.jpacrud.entity.Student;
import com.techlab.jpacrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRep;
	
	
	private PageResponse<Student> studentPageResponse = new PageResponse<Student>();

	 @Override
	    public PageResponse<Student> getAllStudent(int pageNo, int pageSize) {
	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        Page<Student> page= studentRep.findAll(pageable);
	        studentPageResponse.setSize(page.getSize());
	        studentPageResponse.setTotalPages(page.getTotalPages());
	        studentPageResponse.setTotalElements(page.getTotalElements());
	        studentPageResponse.setContent(page.getContent());
	        studentPageResponse.setLastPage(page.isLast());
	        
	        return studentPageResponse;
	 
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
	public Page<Student> findByName(String name,int pageNo,int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return studentRep.findByName(name,pageable);
	}

}
