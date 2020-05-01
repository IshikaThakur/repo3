package com.ecommerceApp.ecommerceApp.dtos;

import com.ecommerceApp.ecommerceApp.validators.ValidGST;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SellerRegistrationDto extends UserRegistrationDto {
    @NotNull
    @NotEmpty
    @Size(min = 15, max = 15)
    private String GST;

    @NotNull
    private String companyName;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "^[789]\\d{9}$", message = "Please enter a valid number!")
    @NotNull
    private String companyContact;


    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }
}