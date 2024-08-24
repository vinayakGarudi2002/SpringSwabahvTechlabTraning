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
@Table(name = "addres")
public class Addres {
	
	@Column(name = "addresId")
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addresId;
	@Column(name="buildingName")
	private String buidingName;
	@Column(name="city")
	private String city;
	@Column(name="pincode")
	private int pincode;
	

}
