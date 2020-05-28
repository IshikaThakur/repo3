package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.category.CategoryMetadataFieldValues;
import com.ecommerceApp.ecommerceApp.entities.category.CategoryMetadataFieldValuesId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryMetadaFieldValuesRepository extends CrudRepository<CategoryMetadataFieldValues, CategoryMetadataFieldValuesId> {
    @Query(value = "select categoryMetadataField_id from CategoryMetadataFieldValues where category_id=:category_id",nativeQuery = true)
    List<Long> getMetadataId(@Param(value = "category_id") Long id);

    @Query(value = "select field_values from CategoryMetadataFieldValues  where " +
            "category_id=:category_id and categoryMetadataField_id=:categoryMetadataField_id",nativeQuery = true)
    String getFieldValuesForCompositeKey(@Param(value = "category_id")Long category_id,
                                         @Param(value = "categoryMetadataField_id") Long category_metadata_id);

    @Query(value = "select * from category_metadata_field_values where " +
            "category_id=:category_id and category_metadata_field_id=:category_metadata_field_id ",nativeQuery = true)
    public CategoryMetadataFieldValues getFieldValues(@Param(value = "category_id")Long category_id,
                                                      @Param(value = "category_metadata_field_id") Long category_metadata_id);


}
