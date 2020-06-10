package com.ecommerceApp.ecommerceApp.mongoDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoController {
    @Autowired
    MongoProductRepository mongoProductRepository;
    @PostMapping(value = "product/add/new")
    public String addProduct(@RequestBody MongoProduct mongoProduct)
    {
        mongoProductRepository.save(mongoProduct);
        return "Added sucessfully";

    }
}
