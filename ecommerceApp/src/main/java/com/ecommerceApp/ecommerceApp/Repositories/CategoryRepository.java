package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    Category findByName(String name);
}
