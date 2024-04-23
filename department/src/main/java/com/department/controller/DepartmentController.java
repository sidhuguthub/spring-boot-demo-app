package com.department.controller;


import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import com.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;


    //localhost:8081/department/create

    @PostMapping("/create")
    public ResponseEntity<String> createDepartment(@RequestBody Department department){
        return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatus.CREATED);
    }

    //localhost:8081/department/getAll

    @GetMapping("/getAll")
    private ResponseEntity<List<DepartmentDto>> listAllDepartment(){
        return new ResponseEntity<>(departmentService.findAllDepartment(), HttpStatus.OK);
    }

    //localhost:8081/department/get/1

    @GetMapping("/get/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("departmentId") long departmentId){
        return new ResponseEntity<>(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
    }

}
