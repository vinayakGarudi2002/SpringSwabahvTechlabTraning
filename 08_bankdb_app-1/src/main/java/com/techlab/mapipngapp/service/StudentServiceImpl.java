package com.techlab.mapipngapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.dto.AddresDto;
import com.techlab.mapipngapp.dto.AddresSchema;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.StudentDto;
import com.techlab.mapipngapp.entity.Addres;
import com.techlab.mapipngapp.entity.Student;
import com.techlab.mapipngapp.repository.StudentRepository;


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
	public List<StudentDto> getAllStudentDto() {
		// TODO Auto-generated method stub
		return studentRep.findAllProjectedBy();
	}
	
	@Override
	public Addres getAddresDtoById(int rollnumber) {
		// TODO Auto-generated method stub
		Optional<Student> student =null;
		
		student=studentRep.findById(rollnumber);
		
		if(student==null) {
			
		}
		
		return student.get().getAddres();
		 
	}
	
	
	@Override
	public Addres updateAddresById(int rollnumber,AddresSchema addres) {
		// TODO Auto-generated method stub
		Optional<Student> student =null;
		
	
		
		student=studentRep.findById(rollnumber);
		
		if(student==null) {
			
		}
		
		
		Student st=student.get();
		Addres prevAddres= student.get().getAddres();
		prevAddres.setBuidingName(addres.getBuidingName());
		prevAddres.setCity(addres.getCity());
		prevAddres.setPincode(addres.getPincode());
		
		st.setAddres(prevAddres);
		
		studentRep.save(st);
		 
		return st.getAddres();
	}
	
	@Override
	public Student addUpdateStudent(Student student) {
		
		return	studentRep.save(student);
		
	}

	 private PageResponse<Student> createPageResponse(Page<Student> paymentPage) {
	        PageResponse<Student> paymentPageResponse = new PageResponse<>();
	        paymentPageResponse.setContent(paymentPage.getContent());
	        paymentPageResponse.setSize(paymentPage.getSize());
	        paymentPageResponse.setTotalElements(paymentPage.getTotalElements());
	        paymentPageResponse.setTotalPages(paymentPage.getTotalPages());
	        paymentPageResponse.setLastPage(paymentPage.isLast());

	        return paymentPageResponse;
	    }
}
