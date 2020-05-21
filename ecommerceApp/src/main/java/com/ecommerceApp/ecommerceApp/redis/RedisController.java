package com.ecommerceApp.ecommerceApp.redis;

import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController

public class RedisController {
    @Autowired
    ProductRedisRepository productRedisRepository;
    @Autowired
    ProductRepository productRepository;

    public RedisController(ProductRedisRepository productRedisRepository) {
        this.productRedisRepository = productRedisRepository;
    }

    @GetMapping("/product/add/{id}/name")
    public Product add(@PathVariable("id") Long id,
                       @PathVariable ("name") String name)
    {
       return productRedisRepository.findById(id);
    }

    @GetMapping("/product/all")
    public Map<String, Product> findAll() {
        return productRedisRepository.findAll();
    }

    @GetMapping("admin/product/one/{id}")
    @Cacheable(value = "product", key = "#id")
    public Product findOne(@PathVariable("id") Long id) {
        return productRedisRepository.findById(id);
    }


    @RequestMapping(value = "product/{productId}", method = RequestMethod.GET)
    @Cacheable(value = "product", key = "#productId")
    public Optional<Product> getUser(@PathVariable Integer productId) {
        return productRepository.findById(Long.valueOf(productId));

    }
    @Cacheable(value = "product", key = "#idname")
    @GetMapping(value = "product/add/{name}")
    public Product adding(@PathVariable("id") Long id,@PathVariable("name") String name)
    {
        productRedisRepository.save(new Product(name,"Gucci","Levis"));
        return productRedisRepository.findById(id);
    }
}

