package com.ecommerceApp.ecommerceApp.exceptions;

public class ProductNotActiveException extends RuntimeException {
    public ProductNotActiveException(String message){
        super(message);
    }
}
