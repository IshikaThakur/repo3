package com.ecommerceApp.ecommerceApp.exceptions;

public class ProductDoesNotExistsException extends RuntimeException {
    public ProductDoesNotExistsException(String message){
        super(message);
    }
}
