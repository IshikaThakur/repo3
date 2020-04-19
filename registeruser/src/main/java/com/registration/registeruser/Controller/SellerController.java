package com.registration.registeruser.Controller;


import com.registration.registeruser.Services.CurrentUserService;
import com.registration.registeruser.Services.SellerService;
import com.registration.registeruser.Services.UserService;
import com.registration.registeruser.dto.AddressDto;
import com.registration.registeruser.dto.CustomerDto;
import com.registration.registeruser.dto.SellerDto;
import com.registration.registeruser.dto.SellerProfileDto;
import com.registration.registeruser.entity.Seller;
import com.registration.registeruser.repository.CustomerRepository;
import com.registration.registeruser.repository.ProductRepository;
import com.registration.registeruser.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class SellerController {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserService userService;
    @Autowired
    SellerService sellerService;

    @GetMapping("/seller/home")
    ResponseEntity sellerHome(){
        String msg="Seller home";
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @GetMapping("/seller/profile")
    SellerProfileDto viewProfile(){
        String username=currentUserService.getUser();
        Seller seller =sellerRepository.findByUsername(username);
        return sellerService.toSellerViewProfileDto(seller);
    }

    @GetMapping("/seller/profile/update")
    String updateprofile(@RequestBody SellerDto sellerDto){
        return sellerService.updateProfile(sellerDto);
    }

    @GetMapping("/seller/password/update")
    String updatePassword(@RequestParam("password") String newPassword){
        String username=currentUserService.getUser();
        return sellerService.updatePassword(username, newPassword);
    }

    @GetMapping("/seller/address/update/{id}")
    String updateAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Long id){
        String username=currentUserService.getUser();
        return sellerService.updateAddress(id, addressDto, username);
    }
}
