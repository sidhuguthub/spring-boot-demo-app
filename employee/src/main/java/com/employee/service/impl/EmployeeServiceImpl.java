package com.employee.service.impl;

import com.employee.dto.Department;
import com.employee.dto.DepartmentEmployees;
import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;
import com.employee.mapper.MapperClass;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MapperClass mapperClass;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public String saveEmployee(long departmentId, Employee employee) {
        Department department = restTemplate.getForObject("http://localhost:8081/department/get/" + departmentId, Department.class);
        employee.setDepartmentId(department.getDepartmentId());
        Employee savedEmployee = employeeRepository.save(employee);
        logger.info("Employee saved successfully : {}", savedEmployee);
        return "Employee saved successfully !";
    }

    @Override
    public DepartmentEmployees findEmployeeByDepartment(long departmentId) {
        Department department = restTemplate.getForObject("http://localhost:8081/department/get/" + departmentId, Department.class);
        List<Employee> all = employeeRepository.findAll();
        logger.info("Found {} employees : ", all.size());
        List<EmployeeDto> collect = all.stream().map(a -> mapperClass.mapToEmployeeDto(a)).collect(Collectors.toList());
        DepartmentEmployees departmentEmployees = new DepartmentEmployees();
        departmentEmployees.setDepartmentName(department.getDepartmentName());
        departmentEmployees.setLocation(department.getLocation());
        departmentEmployees.setEmail(department.getEmail());
        departmentEmployees.setStatus(department.getStatus());
        departmentEmployees.setEmployeeDtoList(collect);
        return departmentEmployees;
    }

    @Override
    public EmployeeDto updateEmployeeData(long departmentId, long employeeId, Employee employee) {
        logger.info("Making a RestTemplate call to fetch department by id : {}", departmentId);
        Department department = restTemplate.getForObject("http://localhost:8081/department/get/" + departmentId, Department.class);
        if(department!=null) {
            logger.info("Department Found : {}", department);
        }else {
            logger.info("No Department found with id number : {}", departmentId);
        }
        Employee employeeFound = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(employeeId));
        employee.setDepartmentId(department.getDepartmentId());
        employee.setEmployeeId(employeeFound.getEmployeeId());
        Employee save = employeeRepository.save(employee);
        logger.info("Updated employee : {}",save);
        return mapperClass.mapToEmployeeDto(save);
    }

}
