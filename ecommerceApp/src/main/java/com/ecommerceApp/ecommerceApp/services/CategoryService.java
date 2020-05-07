package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CategoryMetadataFieldRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CategoryMetadaFieldValuesRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CategoryRepository;
import com.ecommerceApp.ecommerceApp.dtos.BaseDto;
import com.ecommerceApp.ecommerceApp.dtos.ErrorDto;
import com.ecommerceApp.ecommerceApp.dtos.ResponseDto;
import com.ecommerceApp.ecommerceApp.dtos.categorydtos.CategoryDto;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import com.ecommerceApp.ecommerceApp.exceptions.CategoryAlreadyRegisteredException;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMetadataFieldRepository categoryFieldRepository;
    @Autowired
    CategoryMetadaFieldValuesRepository categoryMetadaFieldRepository;
    @Autowired
    ModelMapper modelMapper;
    //=============================Adding new Category================================
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
            throw new CategoryAlreadyRegisteredException("Category Name Already Registered");
        }
        return "Category Added Successfully";
    }
   // =================Updation Of Category====================================
   public ResponseEntity<BaseDto> updateCategory(Long id, String name) {
       BaseDto response;
       Optional<Category> savedCategory = categoryRepository.findById(id);
       if (!savedCategory.isPresent()) {
           response = new ErrorDto("Not found", "Category does not exists");
           return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
       }

       Category category = savedCategory.get();
       category.setName(name);
       categoryRepository.save(category);

       response = new ResponseDto<>("Successfully updated", null);
       return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
   }

//======================View a Category======================
public CategoryDto getCategory(Long id) {
    if (!categoryRepository.findById(id).isPresent()) {
        throw new InvalidDetailException("entered category Id is invalid");
    }
    Category category = categoryRepository.findById(id).get();
    CategoryDto categoryDto = new CategoryDto(category.getId(),category.getName());
    return categoryDto;
}
//=========================View All Categories=============================
public List<CategoryDto> getAll() {
    List<Category> categoryList = categoryRepository.findAll(PageRequest.of(0, 5,
            Sort.Direction.ASC, "id"));
    List<CategoryDto> categoryDtoList = new ArrayList<>();
    categoryList.forEach(categoryDto -> categoryDtoList.add(new CategoryDto(categoryDto.getId(),
            categoryDto.getName())));
    return categoryDtoList;
}
    //==============API to add new metadatafield==============================


}
