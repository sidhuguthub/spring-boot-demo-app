package com.employee.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private long employeeId;

    public ResourceNotFoundException(long employeeId) {
        super("No Resource Found with Id Number : "+employeeId);
    }

}
