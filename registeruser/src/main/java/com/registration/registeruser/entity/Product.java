package com.registration.registeruser.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private Long name;
    private Long description;
    private Long isCancellable;
    private Long brand;
    private Long isActive;
    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(mappedBy = "product")
    private Set<Product_Variation> variations;


    @OneToMany(mappedBy = "product")
    private List<Product_Review> product_reviews;

    public List<Product_Review> getProduct_reviews() {
        return product_reviews;
    }

    public void setProduct_reviews(List<Product_Review> product_reviews) {
        this.product_reviews = product_reviews;
    }

    public Set<Product_Variation> getVariations() {
        return variations;
    }

    public void setVariations(Set<Product_Variation> variations) {
        this.variations = variations;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public Long getDescription() {
        return description;
    }

    public void setDescription(Long description) {
        this.description = description;
    }

    public Long getIsCancellable() {
        return isCancellable;
    }

    public void setIsCancellable(Long isCancellable) {
        this.isCancellable = isCancellable;
    }

    public Long getBrand() {
        return brand;
    }

    public void setBrand(Long brand) {
        this.brand = brand;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
