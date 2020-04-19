package com.registration.registeruser.Controller;


import com.registration.registeruser.Services.CustomerService;
import com.registration.registeruser.Services.UserService;
import com.registration.registeruser.dto.AdminCustomerDto;
import com.registration.registeruser.dto.CustomerDto;
import com.registration.registeruser.dto.SellerDto;
import com.registration.registeruser.entity.Customer;
import com.registration.registeruser.repository.CustomerRepository;
import com.registration.registeruser.repository.SellerRepository;
import com.registration.registeruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class AdminController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SellerRepository sellerRepository;

     @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;



    @GetMapping("/getAllCustomer")
    public List<CustomerDto> getAllCustomer(@PageableDefault(page = 0, value = 10, sort = {"id"}, direction = Sort.Direction.ASC)
                                                   Pageable pageable)
     {

       return customerService.getAllCustomer(pageable);
    }





    @GetMapping("/activate/customer")     //working successfully
    public void activateCustomer(@RequestParam("id") Long id){
        userService.activateUser(id);

    }

    @GetMapping("/deactivate/customer")    //working
    public void deactivateCustomer(@RequestParam("id") Long id){
        userService.deActivateuser(id);
    }

}
