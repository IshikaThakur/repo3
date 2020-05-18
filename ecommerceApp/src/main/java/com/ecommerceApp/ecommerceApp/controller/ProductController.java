package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.criteria.ProductRepositoryCustom;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductSellerDto;
import com.ecommerceApp.ecommerceApp.entities.Report;
import com.ecommerceApp.ecommerceApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepositoryCustom productRepositoryCustom;

    @PostMapping("/seller/product/add")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductSellerDto productSellerDto, HttpServletRequest request) {
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
    public ResponseEntity<String> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    //================API to get a product for seller=========
    @GetMapping("/seller/product/{id}")
    public ProductSellerDto getProductForSeller(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.getProductByIdForSeller(id, email);
    }

    //=========Updation of product seller===============================
    @PostMapping("/seller/product/update/{productId}")
    public String updateProductAsSeller(@PathVariable Long productId, @RequestBody ProductSellerDto productSellerDto) {
        productService.updateProduct(productId, productSellerDto);
        return "product updated successfully";
    }

    //===============Get all products for seller=================
    @GetMapping("/seller/products")
    public ResponseEntity getAllProductsForSeller(@RequestParam(defaultValue = "0") String offset,
                                                  @RequestParam(defaultValue = "10") String size,
                                                  @RequestParam(defaultValue = "id") String sortByField,
                                                  @RequestParam(defaultValue = "ascending") String order,
                                                  @RequestParam(required = false) Long categoryId,
                                                  @RequestParam(required = false) String brand) {
        return productService.getAllProductsForSeller(offset, size, sortByField, order, categoryId, brand);
    }

    @GetMapping("/products")
    public ResponseEntity getProductByIdForAdmin(@RequestParam(defaultValue = "0") String offset,
                                                 @RequestParam(defaultValue = "10") String size,
                                                 @RequestParam(defaultValue = "id") String sortByField,
                                                 @RequestParam(defaultValue = "ascending") String order,
                                                 @RequestParam(required = false) Long categoryId,
                                                 @RequestParam(required = false) String brand) {
        return productService.getAllProductsForAdmin(categoryId, offset, size, sortByField, order, brand);
    }

    /* @GetMapping("/customer/similar-products/{productId}")
     public ResponseEntity getSimilarProductsByProductIdForCustomer(@PathVariable Long productId,
                                                                            @RequestParam(defaultValue = "0") String offset,
                                                                            @RequestParam(defaultValue = "10") String size,
                                                                            @RequestParam(defaultValue = "id") String sortByField,
                                                                            @RequestParam(defaultValue = "ascending") String order){

         return productService.getAllSimilarProductsByProductId(productId, offset, size, sortByField, order);
     }*/
    //=======================API to check report by admin=============================
    @GetMapping(value = "admin/get/report")
    public List<Report> getReport(@RequestParam(defaultValue = "0") String offset,
                                  @RequestParam(defaultValue = "10") String size,
                                  @RequestParam(defaultValue = "id") String sortByField,
                                  @RequestParam(defaultValue = "ascending") String order) {

    return productService.getReport(offset,size,sortByField,order);
}
//=======================Application of criteria Query to fetch report========
  @GetMapping(value = "admin/get/reports")
    public List<Report> getReports()
{
    return productRepositoryCustom.createReport();
}
}
