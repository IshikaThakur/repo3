package com.ecommerceApp.ecommerceApp.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        super(message);
    }

}
