package com.techlab.mapipngapp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.dto.CourseDto;
import com.techlab.mapipngapp.dto.InstructorDto;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.StudentDto;
import com.techlab.mapipngapp.entity.Course;
import com.techlab.mapipngapp.entity.Instructor;
import com.techlab.mapipngapp.entity.Student;
import com.techlab.mapipngapp.exception.NotFoundException;
import com.techlab.mapipngapp.repository.CourseRepository;
import com.techlab.mapipngapp.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService{

	
	@Autowired
	private InstructorRepository instructorRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Override
	public InstructorDto addInstructor(InstructorDto instructorDto) {
		
		Instructor instructor = toInstructorMapper(instructorDto);
		
		instructor= instructorRepo.save(instructor);
		
		return toInstructorDtoMapper(instructor);
	}
	
	private Instructor toInstructorMapper(InstructorDto instructorDto) {
		Instructor instructor = new Instructor();
		instructor.setName(instructorDto.getName());
		instructor.setEmail(instructorDto.getEmail());
		instructor.setQualification(instructorDto.getQualification());
		
		return instructor;
	}
	
	public InstructorDto toInstructorDtoMapper(Instructor instructor) {
		
		InstructorDto instructorDto = new InstructorDto();
		instructorDto.setName(instructor.getName());
		instructorDto.setEmail(instructor.getEmail());
		instructorDto.setQualification(instructor.getQualification());
		instructorDto.setInstructorId(instructor.getInstructorId());
		
		return instructorDto;
	}

	@Override
	public Instructor allocateCourses(int instructorId, List<CourseDto> courses) {
		// TODO Auto-generated method stub
		
		Instructor dbInstructor;
		
		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
		
		if(!optionalInstructor.isPresent()) {
			throw new NotFoundException("Instructor not found witg given id: "+instructorId);
		}
		
		dbInstructor = optionalInstructor.get();
		
		 List<Integer> courseIds = courses.stream().map((c)->c.getCourseId()).toList();
		 
		 List<Course>  dbCourses = dbInstructor.getCourses();
		 
		 
		 courseIds.forEach((courseId)->{
		 
		 Course  course = courseRepo.findById(courseId).orElseThrow(()->new NotFoundException("Course not found with given roll: "+courseId));
		 if(course==null) {
			 throw new NotFoundException("Course not found with given roll: "+courseId);
		 }
		 course.setInstructor(dbInstructor);
		 
		dbCourses.add(course);
		 
		 
		 });
		 
		 dbInstructor.setCourses(dbCourses);
		 
		
		return instructorRepo.save(dbInstructor);
	}

	@Override
	public List<CourseDto> getInstructorCourses(int instructorId) {
		// TODO Auto-generated method stub
		isInstructorExist(instructorId);
		
		List<Course> courses = instructorRepo.findById(instructorId).get().getCourses();
		
		List<CourseDto> courseDtos = new ArrayList<>();
		
		courses.forEach((course)->{
		courseDtos.add(toCourseDtoMapper(course));
		});
		return courseDtos;
	}

	@Override
	public List<InstructorDto> getAllInstructorDtos() {
		// TODO Auto-generated method stub
		
		List<InstructorDto> instructors = new ArrayList<>();
		
		  
		
		List<Instructor> dbInstructors=instructorRepo.findAll();
		
		dbInstructors.forEach((instructor)->{;
		instructors.add(toInstructorDtoMapper(instructor));
		});
		return instructors;
	}
	

	@Override
	public Page<InstructorDto> getAllInstructorDtosPage(int pageNo, int pageSize) {
	    // Create pageable object
	    Pageable pageable = PageRequest.of(pageNo, pageSize);
	    
	    // Fetch page of Instructor entities
	    Page<Instructor> page = instructorRepo.findAll(pageable);
	    
	    // Convert Instructor entities to InstructorDto using Java streams
	    Page<InstructorDto> instructorDtos = page.map(instructor -> {
	        // Here we use a stream to process the conversion
	        return Stream.of(instructor)
	                     .map(this::toInstructorDtoMapper)
	                     .findFirst()
	                     .orElse(null);
	    });
	    
	    return instructorDtos;
	}

	

	


	@Override
	public InstructorDto getInstructorDto(int instructorId) {
		// TODO Auto-generated method stub
		isInstructorExist(instructorId);
		return toInstructorDtoMapper(instructorRepo.findById(instructorId).get());
	}
	
	
	private Instructor isInstructorExist(int instructorId) {
		instructorRepo.findById(instructorId).orElseThrow(()-> new NotFoundException("Instructor not found witg given id: "+instructorId) );
		
		
		return instructorRepo.findById(instructorId).get();
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
public PageResponse<StudentDto> getAllStudentByInstructor(int instructorId, int pageNo, int pageSize) {
   
    Instructor dbInstructor = instructorRepo.findById(instructorId)
        .orElseThrow(() -> new NullPointerException("Instructor not found"));

   
    List<Course> courses = dbInstructor.getCourses();
    if (courses == null || courses.isEmpty()) {
        throw new NullPointerException("No students assigned to this instructor");
    }

   
    Set<Student> students = new HashSet<>();
    courses.forEach(course -> {
        if (course.getStudents() != null) {
            students.addAll(course.getStudents());
        }
    });

    
    List<StudentDto> studentDtos = students.stream()
        .map(this::toStudentDtoMapper)
        .collect(Collectors.toList());

    int totalElements = studentDtos.size();
    int totalPages = (int) Math.ceil((double) totalElements / pageSize);
    int fromIndex = pageNo * pageSize;
    int toIndex = Math.min(fromIndex + pageSize, totalElements);

    
    List<StudentDto> paginatedStudentDtos = studentDtos.subList(fromIndex, toIndex);

    
    PageResponse<StudentDto> response = new PageResponse<>();
    response.setContent(paginatedStudentDtos);
    response.setSize(pageSize);
    response.setTotalElements(totalElements);
    response.setTotalPages(totalPages);
    response.setLastPage(pageNo == totalPages - 1);

    return response;
}


private StudentDto toStudentDtoMapper(Student student) {
	StudentDto studentDto = new StudentDto();
	studentDto.setAge(student.getAge());
	studentDto.setName(student.getName());
	studentDto.setRollnumber(student.getRollnumber());
	
	return studentDto;
}





}
