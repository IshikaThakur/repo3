package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.Repositories.CategoryRepository;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryRedisController {
    @Autowired
    CategoryRepository categoryRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "product/find/{categoryId}", method = RequestMethod.GET)
    @Cacheable(value = "category",key="#categoryId",unless = "#result==null")
    public Optional<Category> getCategory(@PathVariable("categoryId") Long categoryId){
        LOG.info("getting product id " +categoryId);
        return categoryRepository.findById(categoryId);
    }
    @CachePut(value = "product/add/newcategory", key = "#category.id")
    @PutMapping("product/new/update")
    public Category addCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return category;
    }
    @CacheEvict(value = "category", allEntries=true)
    @DeleteMapping("product/delete/{id}")
    public void deleteUserByID(@PathVariable Long id) {
        LOG.info("deleting category with id {}", +id);
        categoryRepository.deleteCategoryById(id);
    }
    @GetMapping(value = "product/category/findAll")
    @Cacheable(value = "category")
    public List<Category> findAllCategories()
    {
        LOG.info("Retrieving List of Categories");
        return categoryRepository.findAll();
    }


}
