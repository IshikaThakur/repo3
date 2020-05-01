package com.ecommerceApp.ecommerceApp.dtos;

public class BaseDto {
    private String message;

    public BaseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "message='" + message + '\'' +
                '}';
    }
}
