package com.ecommerceApp.ecommerceApp.mongoDb;

import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MongoController {
    @Autowired
    MongoProductRepository mongoProductRepository;
    @Autowired
    MongoProductVariationRepository mongoProductVariationRepository;
    @PostMapping(value = "product/add/new")
    public String addProduct(@RequestBody MongoProduct mongoProduct)
    {
        mongoProductRepository.save(mongoProduct);
        return "Added sucessfully";

    }
    @GetMapping(value = "product/get/{name}")
    public List<MongoProduct> getProductByName(@PathVariable  String name)
    {
        return mongoProductRepository.findProductByName(name);
    }
    @PostMapping(value = "product/add/product-variation")
    public String addProductVariation(MongoProductVariation mongoProductVariation)
    {
        mongoProductVariationRepository.save(mongoProductVariation);
        return "Added Sucessfully";
    }
}
