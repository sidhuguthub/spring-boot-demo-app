package com.employee.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private long departmentId;

    private String departmentName;

    private String departmentHead;

    private Date creationDate;

    private String status;
}
