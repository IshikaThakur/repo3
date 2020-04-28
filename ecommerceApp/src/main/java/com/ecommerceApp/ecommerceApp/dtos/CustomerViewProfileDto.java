package com.ecommerceApp.ecommerceApp.dtos;

import com.sun.istack.NotNull;

import javax.validation.constraints.Pattern;

public class CustomerViewProfileDto extends UserViewProfileDto {
    @Pattern(regexp = "^[789]\\d{9}$", message = "Please enter a valid number!")
    @NotNull
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public CustomerViewProfileDto(@Pattern(regexp = "^[789]\\d{9}$", message = "Please enter a valid number!") String contact) {
        this.contact = contact;
    }

    public CustomerViewProfileDto() {

    }

    public CustomerViewProfileDto(@javax.validation.constraints.NotNull String firstName,
                                  @javax.validation.constraints.NotNull String middleName,
                                  @javax.validation.constraints.NotNull String lastName,
                                  Boolean isActive, @Pattern(regexp = "^[789]\\d{9}$",
            message = "Please enter a valid number!") String contact) {
        super(firstName, middleName, lastName, isActive);
        this.contact = contact;
    }
}
