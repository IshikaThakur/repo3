package com.ecommerceApp.ecommerceApp.controller;


import com.ecommerceApp.ecommerceApp.dtos.CustomerRegistrationDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerRegistrationDto;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    @Autowired
    CustomerService customerService;
    @Autowired
    SellerService sellerService;
   @ApiOperation("Registration of customer")
    @PostMapping("/register/customer")
    ResponseEntity registerCustomer(@Valid @RequestBody CustomerRegistrationDto customerRegistrationDto) {

        return customerService.createNewCustomer(customerRegistrationDto);
    }

  @GetMapping("register/confirm")
    String confirmRegistration(@RequestParam("token") String token) {

        return customerService.validateRegistrationToken(token);

    }
    @ApiOperation("Registration of Seller")
    @PostMapping("/register/seller")
    String registerseller(@Valid @RequestBody SellerRegistrationDto sellerRegistrationDto){
        return sellerService.registerSeller(sellerRegistrationDto);
    }
}