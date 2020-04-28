package com.registration.registeruser.repository;


import com.registration.registeruser.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
