package com.ecommerceApp.ecommerceApp.dtos;

import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductSellerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;



public class ProductCustomerViewDto{

    private ProductSellerDto productDto;


    public ProductSellerDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductSellerDto productDto) {
        this.productDto = productDto;
    }

}
