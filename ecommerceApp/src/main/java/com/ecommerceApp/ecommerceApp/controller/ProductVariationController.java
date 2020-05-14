package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.ProductVariationSellerDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductVariationUpdateDto;
import com.ecommerceApp.ecommerceApp.services.ProductVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class ProductVariationController {
    @Autowired
    ProductVariationService productVariationService;
    @PostMapping("/seller/product-variations")
    public ResponseEntity createProductVariation(@RequestBody ProductVariationSellerDto variationDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return productVariationService.saveNewProductVariation(username, variationDto);
    }

    @GetMapping("/seller/product-variation/{id}")
    public ResponseEntity getProductVariationByIdForSeller(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();

        return productVariationService.getProductVariationByIdForSeller(email, id);
    }


    @GetMapping("/seller/product-variations/{productId}")
    public ResponseEntity getAllProductVariationsByProductIdForSeller(@PathVariable Long productId,
                                                                              @RequestParam(defaultValue = "0") String offset,
                                                                              @RequestParam(defaultValue = "10") String size,
                                                                              @RequestParam(defaultValue = "id") String sortByField,
                                                                              @RequestParam(defaultValue = "ascending") String order,
                                                                              HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        System.out.println("called up #############============###########");
        return productVariationService.getAllProductVariationsByProductIdForSeller(email, productId, offset, size, sortByField, order);
    }
    //======================Updation of ProductVariation======================


    @PatchMapping("/seller/product-variation/{variationId}")
    public ResponseEntity updateProductVariationById(@PathVariable Long variationId, @RequestBody ProductVariationUpdateDto variationDto,
            HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productVariationService.updateProductVariationById(variationId, email, variationDto);
    }
}



