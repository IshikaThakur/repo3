package com.RestAssignmentproject.rest.webservices.springbootrest;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    // Ques 6: Implement Exception Handling for resource not found
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class EmployeeNotFoundException extends RuntimeException{
        public EmployeeNotFoundException(String s) {
            super(s);
        }
    }

