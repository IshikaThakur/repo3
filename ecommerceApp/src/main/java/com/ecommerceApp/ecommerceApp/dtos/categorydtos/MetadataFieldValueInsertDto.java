package com.ecommerceApp.ecommerceApp.dtos.categorydtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Set;


public class MetadataFieldValueInsertDto {

    private Long categoryId;
    private List<CategoryMetadataFieldDto> fieldValues;
MetadataFieldValueInsertDto()
{

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
