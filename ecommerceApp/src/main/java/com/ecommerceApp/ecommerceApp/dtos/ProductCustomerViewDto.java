package com.ecommerceApp.ecommerceApp.dtos;

import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductSellerDto;


import java.util.Set;
public class ProductCustomerViewDto{
    public ProductCustomerViewDto()
    {

    }

    private ProductSellerDto productDto;
    private Set<ProductVariationSellerDto> variations;

    public ProductSellerDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductSellerDto productDto) {
        this.productDto = productDto;
    }

    public Set<ProductVariationSellerDto> getVariations() {
        return variations;
    }

    public void setVariations(Set<ProductVariationSellerDto> variations) {
        this.variations = variations;
    }
}





