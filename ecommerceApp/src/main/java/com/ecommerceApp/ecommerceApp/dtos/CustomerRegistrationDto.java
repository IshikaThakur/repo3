package com.ecommerceApp.ecommerceApp.dtos;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerRegistrationDto extends UserRegistrationDto {

    @Pattern(regexp = "^[789]\\d{9}$", message = "Please enter a valid number!")
    @NotNull
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}