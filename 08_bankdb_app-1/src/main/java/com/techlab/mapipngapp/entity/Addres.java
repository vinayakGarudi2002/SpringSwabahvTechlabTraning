package com.techlab.mapipngapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addresId")
    private int addresId;

    @NotBlank(message = "Building name cannot be blank")
    @Size(min = 2, max = 100, message = "Building name must be between 2 and 100 characters")
    @Column(name = "buildingName")
    private String buidingName;

    @NotBlank(message = "City cannot be blank")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City must contain only alphabetic characters and spaces")
    @Column(name = "city")
    private String city;

    @Min(value = 100000, message = "Pincode must be at least 6 digits long")
    @Column(name = "pincode")
    private int pincode;
}
