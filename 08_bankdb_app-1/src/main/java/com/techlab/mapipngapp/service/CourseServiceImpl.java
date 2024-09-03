package com.techlab.mapipngapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.dto.CourseDto;
import com.techlab.mapipngapp.entity.Course;
import com.techlab.mapipngapp.entity.Instructor;
import com.techlab.mapipngapp.entity.Student;
import com.techlab.mapipngapp.exception.NotFoundException;
import com.techlab.mapipngapp.repository.CourseRepository;
import com.techlab.mapipngapp.repository.InstructorRepository;
import com.techlab.mapipngapp.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService{

	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private InstructorRepository instructorRepo;
	
	private static final Logger logger= LoggerFactory.getLogger(CourseServiceImpl.class);
	@Override
	public Course addCourse(CourseDto corseDto) {
		// TODO Auto-generated method stub
		
		Course course = new Course();
		course.setCourseName(corseDto.getCourseName());
		course.setDuration(corseDto.getDuration());
		course.setFees(corseDto.getFees());
		Course dbCourse= courseRepo.save(course);
		logger.info("Course added with id: "+ course.getCourseId());
		return dbCourse;
	}
	@Override
	public Course alloacteInstructorToCourse(int courseId, int instructorId) {
		// TODO Auto-generated method stub
		
		Course dbCourse;
		Optional<Course> optionalCourse = courseRepo.findById(courseId);
		if(!optionalCourse.isPresent()) {
			throw new NotFoundException("Course not found witg given id: "+courseId);
		}
		
		dbCourse=optionalCourse.get();
		
        Instructor dbInstructor;
		
		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
		
		if(!optionalInstructor.isPresent()) {
			throw new NotFoundException("Instructor not found witg given id: "+instructorId);
		}
		
		dbInstructor = optionalInstructor.get();
		dbCourse.setInstructor(dbInstructor);
		Course course= courseRepo.save(dbCourse);
		
		return course;
	}
	
	private CourseDto toCourseDtoMapper(Course course) {
		
		CourseDto courseDto=new CourseDto();
		
		courseDto.setCourseName(course.getCourseName());
		courseDto.setCourseId(course.getCourseId());
		courseDto.setDuration(course.getDuration());
		courseDto.setFees(course.getFees());
	
		return courseDto;
	}
	@Override
	public CourseDto assignStudentsToCourse(int courseId, List<Integer> rollnumbers) {
		// TODO Auto-generated method stub
		Course course = courseRepo.findById(courseId).orElseThrow( ()-> new NullPointerException("Course not fount") ); 
		
		Set<Student> dbCourseStudents = course.getStudents();
		if(dbCourseStudents==null) {
			dbCourseStudents= new HashSet<>();
		}
		  Set<Student>  toAddStudents = new HashSet<>();
		
		rollnumbers.forEach((rollnumber)->{
			
			Student dbStudent = studentRepository.findById(rollnumber).orElseThrow( ()-> new NullPointerException("No student avialable") );
			
		    Set<Course> dbStudentCourses = dbStudent.getCourses();
		    if(dbStudentCourses==null) {
		    	dbStudentCourses= new HashSet<>();
		    }
		    dbStudentCourses.add(course);
		  
			toAddStudents.add(dbStudent);
		});
		
		dbCourseStudents.addAll(toAddStudents);
		
		course.setStudents(dbCourseStudents);
		
		
		return toCourseDtoMapper(courseRepo.save(course));
	}

}
