package com.ecommerceApp.ecommerceApp.dtos.productdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class ProductAdminViewDto {
  public ProductAdminViewDto()
  {

  }
    private ProductSellerDto productDto;
    private List<String> primaryImages;

    public List<String> getPrimaryImages() {
        return primaryImages;
    }

    public void setPrimaryImages(List<String> primaryImages) {
        this.primaryImages = primaryImages;
    }

    public ProductSellerDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductSellerDto productDto) {
        this.productDto = productDto;
    }
}
