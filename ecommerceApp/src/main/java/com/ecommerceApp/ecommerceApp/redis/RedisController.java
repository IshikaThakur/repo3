package com.ecommerceApp.ecommerceApp.redis;

import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController

public class RedisController {
    @Autowired
    ProductRedisRepository productRedisRepository;
    @Autowired
    ProductRepository productRepository;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public RedisController(ProductRedisRepository productRedisRepository) {
        this.productRedisRepository = productRedisRepository;
    }
    @GetMapping("/product/allpro")
    public List<Product> findAllPro() {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        return productList;
    }


    @GetMapping("/product/all")
    public Map<Long, Product> findAll() {
        return productRedisRepository.findAll();
    }
//issue
    @GetMapping("/product/view/{id}")
   // @Cacheable(value = "product",key="#id")id")
    public Product findProduct(@PathVariable("id") Long id) {
        return productRedisRepository.findById(id);
    }


    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    @Cacheable(value = "product",key="#productId")
    public Optional<Product> getUser(@PathVariable Integer productId) {
        LOG.info("Getting user with ID {}.", productId);
        return productRepository.findById(Long.valueOf(productId));
    }
    @GetMapping(value = "/product/new/{id}/{name}")
    //@Cacheable(value = "product",key="#id")
    public Product add(@PathVariable("id") Long id,@PathVariable("name") String name)
    {
        productRedisRepository.save(new Product(id,name,"Gucci"));
        return productRedisRepository.findById(id);
    }
    @GetMapping(value = "/product/update/{id}/{name}/{brand}")

    public Product update(@PathVariable("id") Long id,@PathVariable("name") String name,@PathVariable("brand")String brand)
    {
        productRedisRepository.update(new Product(id,name,brand));
        return productRedisRepository.findById(id);
    }



}