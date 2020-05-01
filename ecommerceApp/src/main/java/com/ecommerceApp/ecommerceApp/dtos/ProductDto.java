package com.ecommerceApp.ecommerceApp.dtos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String brand;

    private boolean isReturnable=false;
    private boolean isCancelleable=false;
    //private boolean isActive;
   // private boolean isDeleted;

    public ProductDto(Long id, String name, String description, String brand, boolean isReturnable, boolean isCancelleable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.isReturnable = isReturnable;
        this.isCancelleable = isCancelleable;
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


}
