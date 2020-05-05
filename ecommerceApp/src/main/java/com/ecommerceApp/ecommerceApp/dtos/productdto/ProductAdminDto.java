package com.ecommerceApp.ecommerceApp.dtos.productdto;


import com.ecommerceApp.ecommerceApp.dtos.categorydtos.CategoryDto;

public class ProductAdminDto {
    private CategoryDto categoryDto;

    public ProductAdminDto(){}

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
