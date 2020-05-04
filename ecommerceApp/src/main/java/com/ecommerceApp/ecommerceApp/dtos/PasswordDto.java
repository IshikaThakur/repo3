package com.ecommerceApp.ecommerceApp.dtos;

import javax.validation.constraints.NotNull;

public class PasswordDto {
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
