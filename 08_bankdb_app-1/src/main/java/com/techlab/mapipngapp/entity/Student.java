package com.techlab.mapipngapp.entity;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="roll_number")
	private int rollnumber;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private int age;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="addresId")
	private Addres addres;
	

}
