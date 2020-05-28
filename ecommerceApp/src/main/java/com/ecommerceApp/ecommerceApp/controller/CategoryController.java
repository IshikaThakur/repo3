package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.BaseDto;
import com.ecommerceApp.ecommerceApp.dtos.categorydtos.CategoryDto;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import com.ecommerceApp.ecommerceApp.entities.category.CategoryMetadataFieldValues;
import com.ecommerceApp.ecommerceApp.services.CategoryMetaDataFieldService;
import com.ecommerceApp.ecommerceApp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryMetaDataFieldService categoryMetaDataFieldService;

    @PostMapping("categories")
    public String addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return "category added successfully";
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<BaseDto> updateCategory(@PathVariable Long id, @RequestParam String name) {
        return categoryService.updateCategory(id, name);
    }

    //===================adding metadata fields===================
    @PostMapping("admin/add/metadata-fields")
    public ResponseEntity addMetaDataField(@RequestParam String fieldName) {
        return categoryMetaDataFieldService.addNewMetadataField(fieldName);
    }

    //=================list of metadata fields
    @GetMapping("/metadata-fields")
    public ResponseEntity<List> getAllMetadataFields(@RequestParam(defaultValue = "0") String offset,
                                                     @RequestParam(defaultValue = "10") String size,
                                                     @RequestParam(defaultValue = "id") String sortByField,
                                                     @RequestParam(defaultValue = "ascending") String order,
                                                     @RequestParam(required = false) Long categoryId) {

        return categoryMetaDataFieldService.getAllMetadataFields(offset, size, sortByField, order, categoryId);


    }

    //==============API to View a Category================================
    @GetMapping("/view/category/{id}")
    public CategoryDto viewCategory(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return categoryService.getCategory(id);
    }

    //================API to view all categories=============================
    @GetMapping("view/all/categories")
    public List<CategoryDto> viewAllCategory() {
        return categoryService.getAll();
    }

    @PostMapping(value = "/admin/metadatavalues/{categoryId}/{fieldId}",produces = "application/json")
    public ResponseEntity addMetadataWithValues(@Valid @RequestBody CategoryMetadataFieldValues values, @PathVariable Long categoryId, @PathVariable Long fieldId){
        return categoryService.addValues(values,categoryId,fieldId);
    }
}
