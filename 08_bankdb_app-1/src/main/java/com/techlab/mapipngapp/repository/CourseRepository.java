package com.techlab.mapipngapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.mapipngapp.entity.Course;
import com.techlab.mapipngapp.entity.Instructor;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
