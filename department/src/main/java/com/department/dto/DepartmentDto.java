package com.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private String departmentName;

    private String departmentHead;

    private Date creationDate;

    private String status;
}
