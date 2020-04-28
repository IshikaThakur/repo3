package com.ecommerceApp.ecommerceApp.dtos;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SellerViewProfileDto extends UserViewProfileDto {

    @Size(min = 15, max = 15)
    private String GST;

    @NotNull
    private String companyName;

    @Pattern(regexp = "^[789]\\d{9}$", message = "Please enter a valid number!")
    @NotNull
    @Size(min = 10, max = 10)
    private String companyContact;

    public SellerViewProfileDto(){

    }
    public SellerViewProfileDto(@NotNull String firstName, @NotNull String middleName,
                                @NotNull String lastName, Boolean isActive, @Size(min = 15, max = 15)
            String GST, String companyName, @Size(min = 10, max = 10) String companyContact) {
        super(firstName, middleName, lastName, isActive);
        this.GST = GST;
        this.companyName = companyName;
        this.companyContact = companyContact;
    }

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
