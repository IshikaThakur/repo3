package com.registration.registeruser.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
}

