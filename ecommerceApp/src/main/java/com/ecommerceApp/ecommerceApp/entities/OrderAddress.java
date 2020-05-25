package com.ecommerceApp.ecommerceApp.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class OrderAddress {
    private Long id;
    private String city;
    private String state;
    private String country;
    private String addressLine;
    private String zipCode;
    private String label;

    public OrderAddress() {
    }

    public OrderAddress(Address address){
        this.id=address.getId();
        this.city=address.getCity();
        this.state= address.getState();
        this.country=address.getCountry();
        this.addressLine=address.getAddressLine();
        this.zipCode=address.getZipCode();
        this.label=address.getLabel();

    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
}


