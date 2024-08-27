package com.techlab.mapipngapp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.dto.AddresSchema;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.StudentDto;
import com.techlab.mapipngapp.entity.Addres;
import com.techlab.mapipngapp.entity.Course;
import com.techlab.mapipngapp.entity.Student;
import com.techlab.mapipngapp.exception.NotFoundException;
import com.techlab.mapipngapp.repository.CourseRepository;
import com.techlab.mapipngapp.repository.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRep;
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return studentRep.findAll();
	}
	
	@Override
	public PageResponse<StudentDto> getAllStudentDto(int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Student> studentPage = studentRep.findAll(pageable);
		PageResponse<StudentDto> response = new PageResponse<>();
		
		response.setSize(studentPage.getSize());
		response.setLastPage(studentPage.isLast());
		response.setTotalElements(studentPage.getTotalElements());
		response.setTotalPages(studentPage.getTotalPages());
		
		List<Student> students = studentPage.getContent();
		List<StudentDto> studentDtos=new ArrayList<>();
		students.forEach((st)->{
			
			studentDtos.add(toStudentDtoMapper(st));
		});
		
		response.setContent(studentDtos);
		
		return response;
	}
	
	@Override
	public Addres getAddresDtoById(int rollnumber) {
		// TODO Auto-generated method stub
		Student student = isStudentExist(rollnumber);
		
		return student.getAddres();
		 
	}
	
	
	@Override
	public Addres updateAddresById(int rollnumber,AddresSchema addres) {
		// TODO Auto-generated method stub
		Optional<Student> student =null;
	
	isStudentExist(rollnumber);
	
		
		student=studentRep.findById(rollnumber);
		
		if(student==null) {
			throw new NotFoundException("Student not found with given roll: "+rollnumber);
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
		if(student.getRollnumber()!=0) {	
			isStudentExist(student.getRollnumber());
			}
		

		
		return	studentRep.save(student);
		
	}
	
	private Student isStudentExist(int rollnumber) {
	  return studentRep.findById(rollnumber).orElseThrow( ()-> new NotFoundException("Student not found with given roll: "+rollnumber) );	
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

	 private StudentDto toStudentDtoMapper(Student student) {
			StudentDto studentDto = new StudentDto();
			studentDto.setAge(student.getAge());
			studentDto.setName(student.getName());
			studentDto.setRollnumber(student.getRollnumber());
			
			return studentDto;
		}
	    
	    
	    private Student toStudentMapper(StudentDto studentDto) {
	    	Student student = new Student();
	    	student.setAge(studentDto.getAge());
	    	student.setName(studentDto.getName());
	    	student.setRollnumber(studentDto.getRollnumber());
	    	
	    	return student;
	    }

		@Override
		public PageResponse<StudentDto> getAllStudents(int paheNo, int pageSize) {
			// TODO Auto-generated method stub
			Pageable pageable = PageRequest.of(paheNo, pageSize);
			Page<Student> studentPage = studentRep.findAll(pageable);
			PageResponse<StudentDto> response = new PageResponse<>();
			
			response.setSize(studentPage.getSize());
			response.setLastPage(studentPage.isLast());
			response.setTotalElements(studentPage.getTotalElements());
			response.setTotalPages(studentPage.getTotalPages());
			
			List<Student> students = studentPage.getContent();
			List<StudentDto> studentDtos=new ArrayList<>();
			students.forEach((st)->{
				
				studentDtos.add(toStudentDtoMapper(st));
			});
			
			response.setContent(studentDtos);
			
			return response;
		}

		@Override
		public StudentDto getStudentByRollNumber(int rollnumber) {
			// TODO Auto-generated method stub
			Student student=studentRep.findById(rollnumber).orElseThrow( ()-> new NullPointerException("Student Not Found")  );
			return toStudentDtoMapper(student);
		}

		@Override
		public StudentDto assignCourses(int rollnumber, List<Integer> courseIds) {
			// TODO Auto-generated method stub
			Student dbStudent = studentRep.findById(rollnumber).orElseThrow( ()-> new NullPointerException("No student avialable") );
			Set<Course> dbStudentCourses = dbStudent.getCourses();
			
			if(dbStudentCourses ==null) {
				dbStudentCourses=new HashSet<>();
			}
			
			Set<Course> toAddCourse = new HashSet<>();
			
			courseIds.forEach((id)->{
				
				Course course = courseRepository.findById(id).orElseThrow( ()-> new NullPointerException("No course avialable") );
				Set<Student> dbCourseStudents = course.getStudents();
				if(dbCourseStudents==null) {
					dbCourseStudents=new HashSet<>();
				}
				
				dbCourseStudents.add(dbStudent);
				
				toAddCourse.add(course);
			});
			
			dbStudentCourses.addAll(toAddCourse);
			
			
			return toStudentDtoMapper(studentRep.save(dbStudent));
		}



	 
	 
	 
}
