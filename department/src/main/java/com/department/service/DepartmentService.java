package com.department.service;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;

import java.util.List;

public interface DepartmentService {
    String saveDepartment(Department department);

    List<DepartmentDto> findAllDepartment();

    Department getDepartmentById(long departmentId);

}
