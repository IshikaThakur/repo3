package com.ecommerceApp.ecommerceApp.entities;

import com.ecommerceApp.ecommerceApp.services.HashMapConverter;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


@Entity
public class ProductVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer quantityAvailable;
    private Double price;
    private String primaryImageName;

    ProductVariation(Long id,Integer quantityAvailable,Double price,String primaryImageName)
    {
        this.id=id;
        this.quantityAvailable=quantityAvailable;
        this.price=price;
        this.primaryImageName=primaryImageName;
    }


    @Convert(converter = HashMapConverter.class)
    private Map<String, String> productAttributes;

    private boolean isDeleted = false;
    private boolean isActive = true;

   ProductVariation()
   {

   }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;


    @OneToMany(mappedBy = "productVariation", fetch = FetchType.EAGER)
    private Set<OrderProduct> orderedProducts;


    public ProductVariation(Integer quantityAvailable, Double price) {
        this.quantityAvailable = quantityAvailable;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<OrderProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Set<OrderProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Map<String, String> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(Map<String, String> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    @Override
    public String toString() {
        return "ProductVariation{" +
                "id=" + id +
                ", quantityAvailable=" + quantityAvailable +
                ", price=" + price +
                ", primaryImageName='" + primaryImageName + '\'' +
                ", productAttributes=" + productAttributes +
                ", isDeleted=" + isDeleted +
                '}';
    }


    public void addOrderProduct(OrderProduct orderProduct){
        if(orderProduct != null){
            if(orderedProducts == null)
                orderedProducts = new LinkedHashSet<>();
            orderedProducts.add(orderProduct);
        }
    }
}
