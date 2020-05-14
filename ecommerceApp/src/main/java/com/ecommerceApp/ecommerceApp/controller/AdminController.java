package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.AddressDto;
import com.ecommerceApp.ecommerceApp.dtos.CustomerDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerDto;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.services.ActivationDeactivationService;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    CustomerService customerService;
    @Autowired
    SellerService sellerService;

    @Autowired
    ActivationDeactivationService activation_deactivation_service;

    @ApiOperation(value = "To view the list of customers by admin")
    @GetMapping("/admin/customers/list_of_all_customers")
    public List<CustomerDto> getAllCustomers(@RequestParam(defaultValue = "0") String offset,
                                             @RequestParam(defaultValue = "10") String size,
                                             @RequestParam(defaultValue = "id") String sortByField,
                                             @RequestParam(required = false) String email) {
        if (email != null)
            return Arrays.asList(customerService.getCustomerByEmail(email));
        return customerService.getAllCustomer(offset, size, sortByField);

    }

    @ApiOperation("To view the list of all sellers by admin")
    @GetMapping("/admin/sellers/list_of_all_sellers")
    public List<SellerDto> getAllSellers(@RequestParam(defaultValue = "0") String offset,
                                         @RequestParam(defaultValue = "10") String size,
                                         @RequestParam(defaultValue = "id") String sortByField,
                                         @RequestParam(required = false) String email) {
        if (email != null)
            return Arrays.asList(sellerService.getSellerByEmail(email));
        return sellerService.getAllSeller(offset, size, sortByField);
    }

    @ApiOperation(value = "To activate the customer by admin")
    @PutMapping("/admin/activate/{id}")
    public String activateUser(@PathVariable Long id, WebRequest webRequest) {
        return activation_deactivation_service.ActivateUser(id, webRequest);
    }

    @ApiOperation(value = "To deactivate the customer by admin")
    @PutMapping("/admin/deactivate/{id}")
    public String deActivateUser(@PathVariable Long id, WebRequest webRequest) {
        return activation_deactivation_service.DeactivateUser(id, webRequest);
    }

    @ApiOperation(value = "To unlock the account of locked customer")
    @PutMapping("/admin/activate/account/{id}")
    public String activateUserAccount(@PathVariable Long id, WebRequest webRequest) {
        return activation_deactivation_service.ActivateUserAccount(id, webRequest);
    }

    @ApiOperation(value = "To activate the seller by admin")
    @PatchMapping("/admin/activate/seller/{id}")
    public ResponseEntity<String> activateSellerAccount(@RequestBody SellerDto sellerDto, @PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return sellerService.activateSellerAccount(id, sellerDto);
    }

    @ApiOperation(value = "To activate the seller by admin")
    @PatchMapping("/admin/deactivate/seller/{id}")
    public ResponseEntity<String> deactivateSellerAccount(@RequestBody SellerDto sellerDto, @PathVariable Long id, HttpServletRequest request) {
        return sellerService.deactivateSellerAccount(id, sellerDto);
    }


}