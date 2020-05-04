package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CategoryFieldRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CategoryRepository;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import com.ecommerceApp.ecommerceApp.exceptions.CategoryAlreadyRegistered;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryFieldRepository categoryFieldRepository;
    public String addCategory(Category category) {
        Category category1 = categoryRepository.findByName(category.getName());
        if(category1!=null){
            throw new InvalidDetailException("category already present");
        }
        try {
            if(category.getId() !=null){
                Category parentCategory = categoryRepository.findById(category.getId()).get();
                Category newCategory = new Category();
                newCategory.setName(category.getName());
                newCategory.setParentCategory(parentCategory.getParentCategory());
                categoryRepository.save(newCategory);}
            else{
                Category newCategory = new Category();
                newCategory.setName(category.getName());
                categoryRepository.save(newCategory);
            }
        } catch (Exception ex) {
            throw new CategoryAlreadyRegistered("Category Name Already Registered");
        }
        return "Category Added Successfully";
    }
}
