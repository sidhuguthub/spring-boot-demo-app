package com.department.service;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import com.department.exception.ResourceNotFoundException;
import com.department.mapper.MapperClass;
import com.department.reporitory.DepartmentRepository;
import com.department.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepo;

    @Mock
    private MapperClass mapperClass;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDepartment(){
        Department department = new Department();
        when(departmentRepo.save(department)).thenReturn(department);

        String result = departmentServiceImpl.saveDepartment(department);
        assertEquals("Department details saved successfully", result);
        verify(departmentRepo, times(1)).save(department);
    }

    @Test
    public void testFindAllDepartment() {
        // Arrange
        List<Department> departmentList = Arrays.asList(new Department(), new Department());
        when(departmentRepo.findAll()).thenReturn(departmentList);

        // Mock the mapper behavior
        when(mapperClass.mapToDepartmentDto(any(Department.class)))
                .thenAnswer(invocation -> {
                    Department department = invocation.getArgument(0);
                    return new DepartmentDto(); // Create a new DTO for the test
                });

        // Act
        List<DepartmentDto> result = departmentServiceImpl.findAllDepartment();

        // Assert
        assertEquals(departmentList.size(), result.size());

        // Verify that mapperClass.mapToDepartmentDto was called the expected number of times
        verify(mapperClass, times(departmentList.size())).mapToDepartmentDto(any(Department.class));
    }

    @Test
    public void testGetDepartmentById_Found() {
        // Arrange
        long departmentId = 1L;
        Department department = new Department();
        when(departmentRepo.findById(departmentId)).thenReturn(Optional.of(department));

        // Act
        Department result = departmentServiceImpl.getDepartmentById(departmentId);

        // Assert
        assertNotNull(result);
        verify(departmentRepo, times(1)).findById(departmentId);
    }

    @Test
    public void testGetDepartmentById_NotFound() {
        // Arrange
        long departmentId = 1L;
        when(departmentRepo.findById(departmentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            departmentServiceImpl.getDepartmentById(departmentId);
        });
        verify(departmentRepo, times(1)).findById(departmentId);
    }
}
