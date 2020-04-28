package com.ecommerceApp.ecommerceApp.entities;

import com.ecommerceApp.ecommerceApp.security.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Customer extends Users {


    private String contact;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<ProductReview> reviews;


    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Orders> orders;

    public Customer() {
        this.addRole(new Role( 3l,"ROLE_CUSTOMER"));
    }


    public Customer(String email, String firstName, String middleName, String lastName, String contact) {
        super(email, firstName, middleName, lastName);
        this.addRole(new Role( 3l,"ROLE_CUSTOMER"));
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ProductReview> reviews) {
        this.reviews = reviews;
    }

    public void addReview(ProductReview review) {
        if (review != null) {
            if (reviews == null)
                reviews = new ArrayList<>();

            reviews.add(review);

            review.setAuthor(this);
        }
    }
}