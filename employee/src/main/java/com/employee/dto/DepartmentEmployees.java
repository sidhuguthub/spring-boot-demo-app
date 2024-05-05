package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEmployees {

    private String departmentName;

    private String location;

    private String email;

    private String status;

    private List<EmployeeDto> employeeDtoList;
}
