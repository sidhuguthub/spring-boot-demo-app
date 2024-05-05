package com.employee.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private String firstName;

    private String lastName;

    private String email;

    private long mobile;

    private String city;

    private String jobTitle;

    private double salary;
}
