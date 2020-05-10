package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CategoryMetadataFieldRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CategoryMetadaFieldValuesRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CategoryRepository;
import com.ecommerceApp.ecommerceApp.dtos.BaseDto;
import com.ecommerceApp.ecommerceApp.dtos.CategoryFilterDto;
import com.ecommerceApp.ecommerceApp.dtos.ErrorDto;
import com.ecommerceApp.ecommerceApp.dtos.ResponseDto;
import com.ecommerceApp.ecommerceApp.dtos.categorydtos.CategoryDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import com.ecommerceApp.ecommerceApp.entities.category.CategoryMetadataField;
import com.ecommerceApp.ecommerceApp.exceptions.CategoryAlreadyRegisteredException;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import com.ecommerceApp.ecommerceApp.exceptions.ProductDoesNotExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMetadataFieldRepository categoryFieldRepository;
    @Autowired
    CategoryMetadaFieldValuesRepository categoryMetadaFieldValuesRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    MessageSource messageSource;

    CategoryDto toCategoryDto(Category category){
        if(category == null)
            return null;
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        CategoryDto parentDto = toCategoryDto(category.getParentCategory());
        categoryDto.setParent(parentDto);
        return categoryDto;
    }

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
//=======Filtering
public CategoryFilterDto getFilteringDetails(Long category_id) {
    Optional<Category> category = categoryRepository.findById(category_id);
    CategoryFilterDto filteringDTO = new CategoryFilterDto();

    if (category.isPresent() ) {
        List<Long> longList = categoryMetadaFieldValuesRepository.getMetadataId(category_id);
        filteringDTO.setCategoryName(category.get().getName());
        List<String> fields = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Long l : longList) {
            Optional<CategoryMetadataField> categoryMetadataField = categoryFieldRepository.findById(l);
            fields.add(categoryMetadataField.get().getName());
            values.add(categoryMetadaFieldValuesRepository.getFieldValuesForCompositeKey(category_id, l));
        }
        filteringDTO.setFields(fields);
        filteringDTO.setValues(values);
        Set<Product> set = category.get().getProducts();
        Double minPrice = 0.0;
        Double maxPrice = 0.0;
        TreeSet<Double> doubles = new TreeSet<>();
        List<String> brands = new ArrayList<>();
        for (Product product : set) {
            brands.add(product.getBrand());
            Set<ProductVariation> set1 = product.getVariations();
            for (ProductVariation productVariation : set1) {
                doubles.add(productVariation.getPrice());
            }
        }
        filteringDTO.setBrands(brands);
        Double[] d = doubles.toArray(new Double[doubles.size()]);
        filteringDTO.setMaximumPrice(d[d.length - 1]);
        filteringDTO.setMinimumPrice(d[0]);
    } else {
        Long[] l =  {};
       throw new ProductDoesNotExistsException("Not found");
    }
    return filteringDTO;
}


}
