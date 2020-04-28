package com.ecommerceApp.ecommerceApp.controller;
import com.ecommerceApp.ecommerceApp.dtos.CustomerDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerDto;
import com.ecommerceApp.ecommerceApp.services.ActivationDeactivationService;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;
/////////////done
@RestController
public class AdminController {
    @Autowired
    CustomerService customerService;
    @Autowired
    SellerService sellerService;

    @Autowired
    ActivationDeactivationService activation_deactivation_service;
    @GetMapping("/admin/customers/list_of_all_customers")
    public List<CustomerDto> getAllCustomers(@RequestParam(defaultValue = "0") String offset,
                                             @RequestParam(defaultValue = "10") String size,
                                             @RequestParam(defaultValue = "id") String sortByField,
                                             @RequestParam(required = false) String email) {
        if (email != null)
            return Arrays.asList(customerService.getCustomerByEmail(email));
        return customerService.getAllCustomer(offset, size, sortByField);

    }

    @GetMapping("/admin/sellers/list_of_all_sellers")
    public List<SellerDto> getAllSellers(@RequestParam(defaultValue = "0") String offset,
                                         @RequestParam(defaultValue = "10") String size,
                                         @RequestParam(defaultValue = "id") String sortByField,
                                         @RequestParam(required = false) String email) {
        if (email != null)
            return Arrays.asList(sellerService.getSellerByEmail(email));
        return sellerService.getAllSeller(offset, size, sortByField);
    }

    @PutMapping("/admin/activate/{id}")
    public String activateUser(@PathVariable Long id, WebRequest webRequest) {
        return activation_deactivation_service.ActivateUser(id, webRequest);
    }

    @PutMapping("/admin/deactivate/{id}")
    public String deActivateUser(@PathVariable Long id, WebRequest webRequest) {
        return activation_deactivation_service.DeactivateUser(id, webRequest);
    }
}