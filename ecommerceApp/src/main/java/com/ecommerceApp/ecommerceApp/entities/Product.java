package com.ecommerceApp.ecommerceApp.entities;

import com.ecommerceApp.ecommerceApp.auditing.Auditable;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity

public class Product  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String brand;

    @Temporal(TemporalType.TIME)
    private Date createdTime;

    private boolean isReturnable=true;
    private boolean isCancelleable=true;
    private boolean isActive=true;
    private boolean isDeleted=false;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_user_id")
    private Seller seller;
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductVariation> variations;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
     @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductReview> reviews;

    {
        isActive = true;
        isCancelleable = true;
        isReturnable = true;
        isDeleted = false;
    }
    public Product(){
        createdTime = new Date();
    }


    public Product(String name, String description, String brand){
        this.name = name;
        this.description = description;
        this.brand = brand;

}
    public Product(Long id,String name,String brand) {
      this.id=id;
      this.name=name;
      this.brand=brand;
    }

    public Product(String productName, String brand, Boolean isCancellable, Boolean isReturnable, String description, boolean isActive) {
        super();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isReturnable() {
        return isReturnable;
    }

    public void setReturnable(boolean returnable) {
        isReturnable = returnable;
    }

    public boolean isCancelleable() {
        return isCancelleable;
    }

    public void setCancelleable(boolean cancelleable) {
        isCancelleable = cancelleable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<ProductVariation> getVariations() {
        return variations;
    }

    public void setVariations(Set<ProductVariation> variations) {
        this.variations = variations;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ProductReview> reviews) {
        this.reviews = reviews;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void addVariation(ProductVariation variation) {
        if (variation != null) {
            if (variations == null)
                variations = new HashSet<>();

            variations.add(variation);
            variation.setProduct(this);
        }
    }

    public void addReview(ProductReview review) {
        if (review != null) {
            if (reviews == null)
                reviews = new ArrayList<>();

            reviews.add(review);

            review.setProduct(this);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", isReturnable=" + isReturnable +
                ", isCancelleable=" + isCancelleable +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", seller=" + seller +
                ", variations=" + variations +
                ", category=" + category +
                ", reviews=" + reviews +
                '}';
    }
}