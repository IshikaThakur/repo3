package com.ecommerceApp.ecommerceApp.redis;

import com.ecommerceApp.ecommerceApp.entities.Product;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class ProductAndCategoryRedisRepoImpl implements ProductRedisRepository {
    private RedisTemplate<String, Product> redisTemplate;
    private HashOperations hashOperations;

    public ProductAndCategoryRedisRepoImpl(RedisTemplate<String, Product> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Product product) {
       hashOperations.put("PRODUCT",product.getId(),product);
    }

    @Override
    public Map<String,Product> findAll() {
        return hashOperations.entries("PRODUCT");
    }

    @Override
    public Product findById(Long id) {
        return (Product) hashOperations.get("PRODUCT",id);
    }

    @Override
    public void update(Product product) {
        save(product);

    }

    @Override
    public void delete(Long id) {
        hashOperations.delete("PRODUCT",id);

    }
}
