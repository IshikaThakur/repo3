package com.ecommerceApp.ecommerceApp.redis;

import com.ecommerceApp.ecommerceApp.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRedisRepository {
    void save(Product product);
    Map<String,Product> findAll();
    Product findById(Long id);
    void update(Product product);
    void delete(Long id);
}
