package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CategoryFieldRepository;
import com.ecommerceApp.ecommerceApp.dtos.BaseDto;
import com.ecommerceApp.ecommerceApp.dtos.ErrorDto;
import com.ecommerceApp.ecommerceApp.dtos.ResponseDto;
import com.ecommerceApp.ecommerceApp.dtos.categorydtos.CategoryMetadataFieldDto;
import com.ecommerceApp.ecommerceApp.entities.category.CategoryMetadataField;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryMetaDataFieldService {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryFieldRepository categoryFieldRepository;
    @Autowired
    ModelMapper modelMapper;
    CategoryMetadataField toCategoryMetadataField(CategoryMetadataFieldDto categoryMetadataFieldDto){
        if(categoryMetadataFieldDto == null)
            return null;
        return modelMapper.map(categoryMetadataFieldDto, CategoryMetadataField.class);
    }
    public CategoryMetadataFieldDto toCategoryMetadataFieldDto(CategoryMetadataField field){
        if(field == null)
            return null;
        return modelMapper.map(field, CategoryMetadataFieldDto.class);
    }

    public ResponseEntity addNewMetadataField(String fieldName) {
        CategoryMetadataField metafield = categoryFieldRepository.findByName(fieldName);
        BaseDto response;
        if(metafield!=null){
            return new ResponseEntity("Invalid Entry, Field Already Exists", HttpStatus.CONFLICT);
        }

        metafield = new CategoryMetadataField();
        metafield.setName(fieldName);
        categoryFieldRepository.save(metafield);
        return new ResponseEntity("Created", HttpStatus.CREATED);
    }


}
