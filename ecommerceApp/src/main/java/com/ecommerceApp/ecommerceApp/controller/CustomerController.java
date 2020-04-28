package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.AddressDto;
import com.ecommerceApp.ecommerceApp.dtos.CustomerViewProfileDto;
import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

        @GetMapping("/customer/view/profile")
            public CustomerViewProfileDto getprofile(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getcustomerProfile(username);
    }

    @GetMapping("/customer/getAll/addresses")
    public Set<AddressDto> getCustomerAddresses(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getCustomerAllAdress(username);
    }

    @PostMapping("/customer/addnew/addresses")
    public ResponseEntity addNewAddress(@Valid @RequestBody AddressDto addressDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.addNewAddress(username,addressDto);
    }

    @PutMapping("/customer/profile")
    public ResponseEntity updateProfile(@Valid @RequestBody CustomerViewProfileDto customerViewProfileDto,
                                        HttpServletRequest httpServletRequest){
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.updateCustomerProfile(username,customerViewProfileDto);
    }

    @DeleteMapping("/customer/delete/addresses/{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.deleteAddress(username, id);
    }
    @PatchMapping("/customer/update/Address/{id}")
    public ResponseEntity<String> updateCustomerAddress(@Valid @RequestBody AddressDto addressDto,
                                                        @PathVariable Long id, HttpServletRequest httpServletRequest)
    {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.updateCustomerAddress(username,addressDto,id);

    }

    @PutMapping("/customer/update/password")
    public String updatePassword(@RequestBody PasswordDto passwordDto){
        customerService.updateCustomerPassword(passwordDto);
        return "Password updated...";
    }
}
