package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.category.CategoryMetadataField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CategoryMetadataFieldRepository extends CrudRepository<CategoryMetadataField, Long> {

    CategoryMetadataField findByName(String fieldName);

    List<CategoryMetadataField> findAll();

    List<CategoryMetadataField> findAll(Pageable pageable);
}
