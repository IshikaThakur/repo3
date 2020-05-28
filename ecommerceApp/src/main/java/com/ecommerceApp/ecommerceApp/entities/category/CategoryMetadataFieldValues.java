package com.ecommerceApp.ecommerceApp.entities.category;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CategoryMetadataFieldValues implements Serializable {
    @EmbeddedId
    private CategoryMetadataFieldValuesId id = new CategoryMetadataFieldValuesId();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("categoryId")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("categoryMetadataFieldId")
    private CategoryMetadataField categoryMetadataField;

    private String value;

    public CategoryMetadataFieldValues(String value)
    {
        this.value = value;
    }

    public CategoryMetadataFieldValues() {

    }

    public CategoryMetadataFieldValues(CategoryMetadataField categoryMetadataField, Category category, String value) {
    }

    public CategoryMetadataFieldValuesId getId() {
        return id;
    }

    public void setId(CategoryMetadataFieldValuesId id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryMetadataField getCategoryMetadataField() {
        return categoryMetadataField;
    }

    public void setCategoryMetadataField(CategoryMetadataField categoryMetadataField) {
        this.categoryMetadataField = categoryMetadataField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CategoryMetadataFieldValues{" +
                "id=" + id +
                ", category=" + category.getName() +
                ", categoryMetadataField=" + categoryMetadataField.getName() +
                ", values='" + value + '\'' +
                '}';
    }


}


