package com.ecommerceApp.ecommerceApp.entities;

import javax.persistence.*;

@Entity
public class ProductVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double price;
    private String primaryImageName;
    private String metadata;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductVariation() {
    }

    public ProductVariation(Integer quantity, Double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantityAvailable() {
        return quantity;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPrimaryImageName() {
        return primaryImageName;
    }

    public void setPrimaryImageName(String primaryImageName) {
        this.primaryImageName = primaryImageName;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductVariation{" +
                "quantityAvailable=" + quantity +
                ", price=" + price +
                ", primaryImageName='" + primaryImageName + '\'' +
                ", metadata='" + metadata + '\'' +
                ", product=" + product +
                '}';
    }
}