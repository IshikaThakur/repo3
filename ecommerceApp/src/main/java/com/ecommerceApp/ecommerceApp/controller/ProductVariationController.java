package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.BaseDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductvariationSellerDto;
import com.ecommerceApp.ecommerceApp.services.ProductVariationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
public class ProductVariationController {
    private static final Logger logger= LoggerFactory.getLogger(ProductVariationController.class);
    @Autowired
    ProductVariationService productVariationService;



        @PostMapping("/seller/product-variations")
        public ResponseEntity<BaseDto> createProductVariation(@RequestBody ProductvariationSellerDto productvariationSellerDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return productVariationService.saveNewProductVariation(username,productvariationSellerDto);
    }

    }



