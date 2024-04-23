package com.employee.service;

import com.employee.dto.Department;
import com.employee.dto.DepartmentEmployees;
import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.mapper.MapperClass;
import com.employee.repository.EmployeeRepository;
import com.employee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepo;

    @Mock
    private MapperClass mapperClass;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testSaveEmployee() {
        // Arrange
        long departmentId = 1L;
        Employee employee = new Employee();
        Department department = new Department();
        department.setDepartmentId(departmentId);

        when(restTemplate.getForObject("http://localhost:8081/department/get/" + departmentId, Department.class))
                .thenReturn(department);

        when(employeeRepo.save(employee)).thenReturn(employee);

        // Act
        String result = employeeServiceImpl.saveEmployee(departmentId, employee);

        // Assert
        assertEquals("Employee saved successfully !", result);
        verify(restTemplate, times(1)).getForObject("http://localhost:8081/department/get/" + departmentId, Department.class);
        verify(employeeRepo, times(1)).save(employee);
    }

    @Test
    public void testFindEmployeeByDepartment() {
        // Arrange
        long departmentId = 1L;
        Department department = new Department();
        department.setDepartmentId(departmentId);

        when(restTemplate.getForObject("http://localhost:8081/department/get/" + departmentId, Department.class))
                .thenReturn(department);

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepo.findAll()).thenReturn(employees);

        EmployeeDto employeeDto1 = new EmployeeDto();
        EmployeeDto employeeDto2 = new EmployeeDto();

        when(mapperClass.mapToEmployeeDto(employee1)).thenReturn(employeeDto1);
        when(mapperClass.mapToEmployeeDto(employee2)).thenReturn(employeeDto2);

        // Act
        DepartmentEmployees departmentEmployees = employeeServiceImpl.findEmployeeByDepartment(departmentId);

        // Assert
        assertNotNull(departmentEmployees);
        assertEquals(department.getDepartmentName(), departmentEmployees.getDepartmentName());
        assertEquals(department.getDepartmentHead(), departmentEmployees.getDepartmentHead());
        assertEquals(department.getCreationDate(), departmentEmployees.getCreationDate());
        assertEquals(department.getStatus(), departmentEmployees.getStatus());
        assertEquals(2, departmentEmployees.getEmployeeDtoList().size());

        verify(restTemplate, times(1)).getForObject("http://localhost:8081/department/get/" + departmentId, Department.class);
        verify(employeeRepo, times(1)).findAll();
        verify(mapperClass, times(2)).mapToEmployeeDto(any(Employee.class));
    }

    @Test
    public void testUpdateEmployeeData() {
        // Arrange
        long departmentId = 1L;
        long employeeId = 1L;
        Employee employee = new Employee();
        Department department = new Department();
        department.setDepartmentId(departmentId);
        Employee existingEmployee = new Employee();
        existingEmployee.setEmployeeId(employeeId);

        when(restTemplate.getForObject("http://localhost:8081/department/get/" + departmentId, Department.class))
                .thenReturn(department);

        when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(existingEmployee));

        when(employeeRepo.save(employee)).thenReturn(employee);
        EmployeeDto employeeDto = new EmployeeDto();
        when(mapperClass.mapToEmployeeDto(employee)).thenReturn(employeeDto);

        // Act
        EmployeeDto result = employeeServiceImpl.updateEmployeeData(departmentId, employeeId, employee);

        // Assert
        assertNotNull(result);
        verify(restTemplate, times(1)).getForObject("http://localhost:8081/department/get/" + departmentId, Department.class);
        verify(employeeRepo, times(1)).findById(employeeId);
        verify(employeeRepo, times(1)).save(employee);
        verify(mapperClass, times(1)).mapToEmployeeDto(employee);
    }
}
