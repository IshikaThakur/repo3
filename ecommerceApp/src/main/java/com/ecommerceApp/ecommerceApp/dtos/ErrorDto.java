package com.ecommerceApp.ecommerceApp.dtos;

public class ErrorDto extends BaseDto {
    private String errors;

    public ErrorDto(String errors, String message) {
        super(message);
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorDto{" +
                "errors=" + errors +
                '}';
    }
}
