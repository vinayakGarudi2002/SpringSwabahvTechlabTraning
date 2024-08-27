package com.techlab.mapipngapp.dto;

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
public class AddresSchema {

    @NotBlank(message = "Building name cannot be blank")
    @Size(min = 2, max = 100, message = "Building name must be between 2 and 100 characters")
    private String buidingName;

    @NotBlank(message = "City cannot be blank")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City must contain only alphabetic characters and spaces")
    private String city;

    @Min(value = 100000, message = "Pincode must be at least 6 digits long")
    private int pincode;
}
