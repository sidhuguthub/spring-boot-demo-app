package com.department.service.impl;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import com.department.exception.ResourceNotFoundException;
import com.department.mapper.MapperClass;
import com.department.reporitory.DepartmentRepository;
import com.department.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private MapperClass mapperClass;

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Override
    public String saveDepartment(Department department) {
        departmentRepo.save(department);
        logger.info("Department saved successfully : {}", department);
        return "Department details saved successfully";
    }

    @Override
    public List<DepartmentDto> findAllDepartment() {
        List<Department> all = departmentRepo.findAll();
        logger.info("Found {} departments ", all.size());
        return all.stream().map(a-> mapperClass.mapToDepartmentDto(a)).collect(Collectors.toList());
    }

    @Override
    public Department getDepartmentById(long departmentId) {
        Department department = departmentRepo.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException(departmentId));
        logger.info("Department found : {}", department);
        return department;
    }

}
