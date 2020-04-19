package com.registration.registeruser.Controller;


import com.registration.registeruser.Services.CurrentUserService;
import com.registration.registeruser.dto.AddressDto;
import com.registration.registeruser.Services.CustomerService;
import com.registration.registeruser.Services.UserService;
import com.registration.registeruser.dto.CustomerDto;
import com.registration.registeruser.dto.CustomerProfileDto;
import com.registration.registeruser.dto.UserProfileDto;
import com.registration.registeruser.entity.Address;
import com.registration.registeruser.entity.Customer;
import com.registration.registeruser.entity.Product;
import com.registration.registeruser.repository.CustomerRepository;
import com.registration.registeruser.repository.ProductRepository;
import com.registration.registeruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class CustomerController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CurrentUserService currentUserService;


        @GetMapping("/customer/home")
        ResponseEntity customerhome(){
            List<Product> productList=new ArrayList<>();
            productList= (List<Product>) productRepository.findAll();
            String msg="customer home";
            return new ResponseEntity(msg, HttpStatus.OK);
        }

        @GetMapping("/customer/profile")
        CustomerProfileDto viewprofile(){
            String username=currentUserService.getUser();
            Customer customer=customerRepository.findByUsername(username);
            return customerService.toCustomerViewProfileDto(customer);
        }


        @GetMapping("/customer/profile/update")
        void updateprofile(@RequestBody CustomerDto customerDto){
            userService.updateProfile(customerDto);
        }



        @GetMapping("/customer/address/add")
        String addAddress(@Valid @RequestBody AddressDto addressDto){
            String username=currentUserService.getUser();
            Customer customer=customerRepository.findByUsername(username);
            return customerService.addAddress(customer, addressDto);
        }

        @GetMapping("/customer/address/delete")
        String passwordDelete(@RequestParam("id") Long id){
            String username=currentUserService.getUser();
            return customerService.deleteAddress(id, username);
        }

        @GetMapping("/customer/address/update/{id}")
        String updateAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Long id){
            String username=currentUserService.getUser();
            return customerService.updateAddress(id, addressDto, username);
        }

        @GetMapping("/customer/password/update")
        String updatePassword(@RequestParam("password") String newPassword){
            String username=currentUserService.getUser();
            return customerService.updatePassword(username, newPassword);
        }
    }
