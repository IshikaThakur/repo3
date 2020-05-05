package com.ecommerceApp.ecommerceApp.dtos.productdto;


import com.ecommerceApp.ecommerceApp.dtos.categorydtos.CategoryDto;

public class ProductCustomerDto {
    private CategoryDto categoryDto;

    public ProductCustomerDto(){}


    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
