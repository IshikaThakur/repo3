package com.ecommerceApp.ecommerceApp.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CartDto {
   // private Long id;
    @NotEmpty
    @NotNull
    private Integer quantity;
    @NotEmpty
    @NotNull
    private Long productVarId;

    public CartDto( Integer quantity, Long productVarId) {

        this.quantity = quantity;
        this.productVarId = productVarId;
    }

    public Long getProductVarId() {
        return productVarId;
    }

    public void setProductVarId(Long productVarId) {
        this.productVarId = productVarId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



}
