package com.department.mapper;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperClass {

    @Autowired
    private ModelMapper modelMapper;

    public DepartmentDto mapToDepartmentDto(Department department){
        return modelMapper.map(department, DepartmentDto.class);
    }
}
