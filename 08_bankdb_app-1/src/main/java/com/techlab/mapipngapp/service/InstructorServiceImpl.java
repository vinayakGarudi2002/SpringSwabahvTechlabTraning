package com.techlab.mapipngapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.dto.CourseDto;
import com.techlab.mapipngapp.dto.InstructorDto;
import com.techlab.mapipngapp.entity.Course;
import com.techlab.mapipngapp.entity.Instructor;
import com.techlab.mapipngapp.repository.CourseRepository;
import com.techlab.mapipngapp.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService{

	
	@Autowired
	private InstructorRepository instructorRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Override
	public Instructor addInstructor(InstructorDto instructorDto) {
		
		Instructor instructor = new Instructor();
		instructor.setName(instructorDto.getName());
		instructor.setEmail(instructorDto.getEmail());
		instructor.setQualification(instructorDto.getQualification());
		
		return instructorRepo.save(instructor);
	}

	@Override
	public Instructor allocateCourses(int instructorId, List<CourseDto> courses) {
		// TODO Auto-generated method stub
		
		Instructor dbInstructor;
		
		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
		
		if(!optionalInstructor.isPresent()) {
			return null;
		}
		
		dbInstructor = optionalInstructor.get();
		
		 List<Integer> courseIds = courses.stream().map((c)->c.getCourseId()).toList();
		 
		 List<Course>  dbCourses = dbInstructor.getCourses();
		 
		 
		 courseIds.forEach((courseId)->{
		 
		 Course  course = courseRepo.findById(courseId).get();
		 course.setInstructor(dbInstructor);
		 
		dbCourses.add(course);
		 
		 
		 });
		 
		 dbInstructor.setCourses(dbCourses);
		 
		
		return instructorRepo.save(dbInstructor);
	}
}
