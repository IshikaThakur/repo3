package com.ecommerceApp.ecommerceApp.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressDto {
    @NotNull
    @NotEmpty
    private String addressLine;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @NotEmpty
    private String state;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 6, message = "Zipcode should be of length 6")
    private String zipCode;
    @NotNull
    @NotEmpty
    private String country;
    @NotNull
    @NotEmpty
    private String label;

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public AddressDto(){}

    public AddressDto(String addressLine, String city, String state, @Size(min = 6, max = 6, message = "Zipcode should be of length 6") String zipCode, String country, String label) {
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.label = label;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "addressLine='" + addressLine + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}