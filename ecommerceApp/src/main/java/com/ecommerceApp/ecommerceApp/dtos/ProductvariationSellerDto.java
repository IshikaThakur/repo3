package com.ecommerceApp.ecommerceApp.dtos;

import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductSellerDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductvariationSellerDto {
    private Long id;
    @NotNull
    private Long productId;
    private Integer quantity;
    private Double price;
    @NotNull
    private Map<String,String> metadata=new LinkedHashMap<>();
    private ProductSellerDto productDto;

    public ProductSellerDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductSellerDto productDto) {
        this.productDto = productDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
