package com.employee.mapper;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperClass {

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDto mapToEmployeeDto(Employee employee){
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
