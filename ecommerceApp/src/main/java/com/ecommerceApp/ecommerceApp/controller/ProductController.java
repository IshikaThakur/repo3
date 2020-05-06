package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductSellerDto;
import com.ecommerceApp.ecommerceApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/seller/product/add")
    public ResponseEntity<String> createProduct(@RequestBody ProductSellerDto productSellerDto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return productService.addProduct(username, productSellerDto);
    }

    @PutMapping("/product/activate/{id}")
    public ResponseEntity<String> activateProduct(@PathVariable Long id) {
        return productService.activateProductById(id);
    }

    @PutMapping("/product/deactivate/{id}")
    public ResponseEntity<String> deactivateProduct(@PathVariable Long id) {
        return productService.deactivateproductById(id);
    }
//=============Api to delete the product by seller
    @DeleteMapping("/seller/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.deleteProductById(id, email);
    }
    //================API to get product by Id for Admin
    @GetMapping("/admin/product/{productId}")
    public ResponseEntity<String> getProductById(@PathVariable Long productId){
        return productService.getProductById(productId);
    }
    
    //================API to get a product for seller=========
    @GetMapping("/seller/product/{id}")
    public ProductSellerDto getProductForSeller(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.getProductByIdForSeller(id, email);
    }

}
