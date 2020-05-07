package com.ecommerceApp.ecommerceApp.dtos;

import com.ecommerceApp.ecommerceApp.dtos.categorydtos.CategoryMetadataFieldDto;

import java.util.List;

public class CategoryMetadataFieldValuesDto {
    private Long categoryId;
    private List<CategoryMetadataFieldDto> fieldValues;

    public CategoryMetadataFieldValuesDto(){}

    public CategoryMetadataFieldValuesDto(Long categoryId, List<CategoryMetadataFieldDto> fieldValues) {
        this.categoryId = categoryId;
        this.fieldValues = fieldValues;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<CategoryMetadataFieldDto> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<CategoryMetadataFieldDto> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
