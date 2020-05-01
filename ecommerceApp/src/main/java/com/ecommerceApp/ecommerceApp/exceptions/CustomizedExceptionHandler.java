package com.ecommerceApp.ecommerceApp.exceptions;

import com.ecommerceApp.ecommerceApp.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class CustomizedExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public final ResponseEntity<Object> handleEmailAlreadyExistsException(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDto, HttpStatus.CONFLICT);
    }
}
