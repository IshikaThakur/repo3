package com.ecommerceApp.ecommerceApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryAlreadyRegisteredException extends RuntimeException {
    public CategoryAlreadyRegisteredException(String message) {
        super(message);
}}
