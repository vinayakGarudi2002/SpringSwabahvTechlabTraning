package com.techlab.jpacrud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="students")
public class Student {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="roll_number")
	private int rollnumber;
	
	
	@Column(name="name")
	@NotBlank(message = "Name is mandatory")
	private String name;
	@Min(value = 12,message="age need to be atLeast 12")
	@Column(name="age")
	private int age;
	
}
