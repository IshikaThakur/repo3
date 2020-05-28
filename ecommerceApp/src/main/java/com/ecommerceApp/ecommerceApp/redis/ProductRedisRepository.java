package com.ecommerceApp.ecommerceApp.redis;

import com.ecommerceApp.ecommerceApp.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRedisRepository {
    public void save(Product product);

   public Map<Long, Product> findAll();

    public Product findById(Long id);

    public void delete(Long id);
    public void update(Product product);

}
