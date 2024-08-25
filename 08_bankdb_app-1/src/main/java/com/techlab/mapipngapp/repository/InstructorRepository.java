package com.techlab.mapipngapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.mapipngapp.entity.Instructor;
import com.techlab.mapipngapp.entity.Student;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>{

}
