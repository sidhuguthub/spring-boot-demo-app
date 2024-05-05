package com.employee.controller;

import com.employee.dto.DepartmentEmployees;
import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //localhost:8082/employee/create/1

    @PostMapping("/create/{departmentId}")
    public ResponseEntity<String> createEmployee(@PathVariable("departmentId") long departmentId,
                                                 @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.saveEmployee(departmentId, employee), HttpStatus.CREATED);
    }

    //localhost:8082/employee/getByDepartment/1

    @GetMapping("/getByDepartment/{departmentId}")
    public ResponseEntity<DepartmentEmployees> getDepartmentWithEmployees(@PathVariable("departmentId") long departmentId){
        return new ResponseEntity<>(employeeService.findEmployeeByDepartment(departmentId), HttpStatus.OK);
    }

    //localhost:8082/employee/update/1/1

    @PutMapping("/update/{departmentId}/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("departmentId") long departmentId,
                                                      @PathVariable("employeeId") long employeeId,
                                                      @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployeeData(departmentId, employeeId, employee), HttpStatus.OK);
    }
}
