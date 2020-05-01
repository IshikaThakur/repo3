package com.ecommerceApp.ecommerceApp.controller;


import com.ecommerceApp.ecommerceApp.dtos.ProductDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/seller/product/add")
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "product added successfully";
    }

    @GetMapping("/seller/product/view/{productId}")
    public Optional<Product> viewProductAsSeller(@PathVariable Long productId) {
        Optional<Product> product = productService.viewProduct(productId);
        return product;
    }

    @GetMapping("/seller/viewAll/Product")
    public List<Product> viewAllProductAsSeller() {
        return productService.viewAllProductAsSeller();
    }

    @DeleteMapping("/seller/product/delete/{productId}")
    public String deleteProductAsSeller(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return "product deleted successfully";
    }

    @PostMapping("/seller/product/update/{productId}")
    public String updateProductAsSeller(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        productService.updateProduct(productId,productDto);
        return "product updated successfully";
    }
}