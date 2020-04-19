package com.registration.registeruser.repository;

import com.registration.registeruser.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
