package com.techlab.mapipngapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "instructors")
public class Instructor {
	

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="instructorId")
	private int InstructorId;
	
	@Column(name="name")	
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="qualification")
	private String qualification;

}
