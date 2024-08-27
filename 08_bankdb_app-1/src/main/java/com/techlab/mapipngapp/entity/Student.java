package com.techlab.mapipngapp.entity;

import java.sql.Date;
import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roll_number")
    private int rollnumber;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only alphabetic characters and spaces")
    @Column(name = "name")
    private String name;

    @Min(value = 1, message = "Age must be greater than 0")
    @Column(name = "age")
    private int age;

    @NotNull(message = "Address cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Addres addres;
    @ManyToMany( fetch = FetchType.LAZY)
 	@JoinTable(name="student-course", joinColumns = @JoinColumn(name="rollnumber"),inverseJoinColumns = @JoinColumn(name="courseId"))
	private Set<Course> courses;
}
