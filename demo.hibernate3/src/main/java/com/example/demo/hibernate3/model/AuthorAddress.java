package com.example.demo.hibernate3.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AuthorAddress {
     @Column(name="streetnumber")
    private String streetNumber;
    private String location;
    private String state;


    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
