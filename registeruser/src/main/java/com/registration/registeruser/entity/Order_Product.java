package com.registration.registeruser.entity;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;
@Entity
@Table
public class Order_Product {
    @Id
    @GeneratedValue
    private Long id;
    private Integer quantity;
    private Long price;
    private Long productVariationMetadata;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    Orders orders;

    @OneToOne(mappedBy = "order_product")
    Product_Variation product_variation;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ORDER_PRODUCT_ID")
    private Order_Status order_status;

    public Order_Status getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Order_Status order_status) {
        this.order_status = order_status;
    }

    public Product_Variation getProduct_variation() {
        return product_variation;
    }

    public void setProduct_variation(Product_Variation product_variation) {
        this.product_variation = product_variation;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getProductVariationMetadata() {
        return productVariationMetadata;
    }

    public void setProductVariationMetadata(Long productVariationMetadata) {
        this.productVariationMetadata = productVariationMetadata;
    }
}