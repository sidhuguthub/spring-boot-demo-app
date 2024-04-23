package com.department.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private long departmentId;

    public ResourceNotFoundException(long departmentId) {
        super("No Resource Found with Id Number : "+departmentId);
    }

}
