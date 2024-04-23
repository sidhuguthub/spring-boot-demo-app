package com.employee.service;


import com.employee.dto.DepartmentEmployees;
import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;

public interface EmployeeService {
    String saveEmployee(long departmentId, Employee employee);

    DepartmentEmployees findEmployeeByDepartment(long departmentId);

    EmployeeDto updateEmployeeData(long departmentId, long employeeId, Employee employee);
}
