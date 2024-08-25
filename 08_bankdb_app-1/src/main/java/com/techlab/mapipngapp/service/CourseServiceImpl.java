package com.techlab.mapipngapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.dto.CourseDto;
import com.techlab.mapipngapp.entity.Course;
import com.techlab.mapipngapp.entity.Instructor;
import com.techlab.mapipngapp.repository.CourseRepository;
import com.techlab.mapipngapp.repository.InstructorRepository;

@Service
public class CourseServiceImpl implements CourseService{

	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private InstructorRepository instructorRepo;
	@Override
	public Course addCourse(CourseDto corseDto) {
		// TODO Auto-generated method stub
		
		Course course = new Course();
		course.setCourseName(corseDto.getCourseName());
		course.setDuration(corseDto.getDuration());
		course.setFees(corseDto.getFees());
		return courseRepo.save(course);
	}
	@Override
	public Course alloacteInstructorToCourse(int courseId, int instructorId) {
		// TODO Auto-generated method stub
		
		Course dbCourse;
		Optional<Course> optionalCourse = courseRepo.findById(courseId);
		if(!optionalCourse.isPresent()) {
			return null;
		}
		
		dbCourse=optionalCourse.get();
		
        Instructor dbInstructor;
		
		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
		
		if(!optionalInstructor.isPresent()) {
			return null;
		}
		
		dbInstructor = optionalInstructor.get();
		dbCourse.setInstructor(dbInstructor);
		return courseRepo.save(dbCourse);
	}

}
