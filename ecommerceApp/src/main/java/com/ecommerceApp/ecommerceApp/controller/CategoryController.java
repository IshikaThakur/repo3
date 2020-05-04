package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import com.ecommerceApp.ecommerceApp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("categories")
    public String addProduct(@RequestBody Category category) {
       categoryService.addCategory(category);
        return "product added successfully";
    }


}
