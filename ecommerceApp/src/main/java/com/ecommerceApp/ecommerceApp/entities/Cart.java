package com.ecommerceApp.ecommerceApp.entities;

import javax.persistence.*;
import java.util.HashSet;

@Entity
public class Cart  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // @Column(name = "QUANTITY")
    private Integer quantity;
   // @Column(name = "IS_WISHLIST_ITEM")
    private Boolean isWishlistItem;

  // @OneToOne
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_VARIATION_ID")
    private ProductVariation productVariation;

  //  @OneToOne
  //  @JoinColumn(name = "CUSTOMER_USER_ID")
 //   private Users users;
    @OneToOne
    @JoinColumn(name = "customer_id")
     Customer customer;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Boolean getWishlistItem() {
        return isWishlistItem;
    }

    public void setWishlistItem(Boolean wishlistItem) {
        isWishlistItem = wishlistItem;
    }
    }
