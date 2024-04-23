package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEmployees {

    private String departmentName;

    private String departmentHead;

    private Date creationDate;

    private String status;

    private List<EmployeeDto> employeeDtoList;
}
