package com.ecommerceApp.ecommerceApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDetailException extends RuntimeException {
    public InvalidDetailException(String message) {
        super(message);
    }

}
