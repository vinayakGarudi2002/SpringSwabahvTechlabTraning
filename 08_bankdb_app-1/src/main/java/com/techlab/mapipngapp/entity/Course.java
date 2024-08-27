package com.techlab.mapipngapp.entity;

import java.util.Set;

import org.hibernate.annotations.ManyToAny;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    private int courseId;

    @NotBlank(message = "Course name cannot be blank")
    @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
    @Column(name = "courseName")
    private String courseName;

    @Min(value = 1, message = "Duration must be at least 1")
    @Column(name = "duration")
    private int duration;

    @DecimalMin(value = "0.0", inclusive = false, message = "Fees must be greater than 0")
    @Column(name = "fees")
    private double fees;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "instructorId")
    private Instructor instructor;
    
    @ManyToMany(mappedBy = "courses")
	private Set<Student> students;
}
