package com.ecommerceApp.ecommerceApp.dtos;

import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductSellerDto;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ProductVariationSellerDto {

    private Long id;

    @NotNull
    private Long productId;
    private Integer quantityAvailable;
    private Double price;

    private String primaryImage;
    private List<String> secondaryImages;

    @NotNull
    private Map<String, String> attributes = new LinkedHashMap<>();

    private ProductSellerDto productDto;

  ProductVariationSellerDto()
  {

  }
    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    public ProductSellerDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductSellerDto productDto) {
        this.productDto = productDto;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public List<String> getSecondaryImages() {
        return secondaryImages;
    }

    public void setSecondaryImages(List<String> secondaryImages) {
        this.secondaryImages = secondaryImages;
    }
}

