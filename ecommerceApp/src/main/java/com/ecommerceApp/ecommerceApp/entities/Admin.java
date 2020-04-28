package com.ecommerceApp.ecommerceApp.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "USERS_ID")
public class Admin extends Users {
    public Admin() {

    }

    public Admin(String email, String firstName, String middleName, String lastName) {
        super(email, firstName, middleName, lastName);
    }
}
