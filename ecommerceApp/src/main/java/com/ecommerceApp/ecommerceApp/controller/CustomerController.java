package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.AddressDto;
import com.ecommerceApp.ecommerceApp.dtos.CustomerViewProfileDto;
import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.entities.Address;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

       @ApiOperation(value = "shows customer profile",notes="shows the profile for the logged in customer",authorizations = {@Authorization(value = "Bearer")})
        @GetMapping("/customer/view/profile")
            public CustomerViewProfileDto getprofile(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getcustomerProfile(username);
    }
    @ApiOperation("To get all addresses of customer")
    @GetMapping("/customer/getAll/addresses")
    public Set<AddressDto> getCustomerAddresses(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getCustomerAllAdress(username);
    }
    @ApiOperation("To add new address for customer")
    @PostMapping("/customer/addnew/addresses")
    public ResponseEntity addNewAddress(@Valid @RequestBody AddressDto addressDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.addNewAddress(username,addressDto);
    }
    @ApiOperation("To view customer profile")
    @PutMapping("/customer/profile")
    public ResponseEntity updateProfile(@Valid @RequestBody CustomerViewProfileDto customerViewProfileDto,
                                        HttpServletRequest httpServletRequest){
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.updateCustomerProfile(username,customerViewProfileDto);
    }
   /* @ApiOperation("To  delete address of customer")
    @DeleteMapping("/customer/delete/addresses/{id}")
     public ResponseEntity<String> deleteAddressById(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.deleteAddress(username, id);
    }*/
    @ApiOperation("To update address of customer")
    @PatchMapping("/customer/update/Address/{id}")
    public ResponseEntity<String> updateCustomerAddress(@Valid @RequestBody AddressDto addressDto,
                                                        @PathVariable Long id, HttpServletRequest httpServletRequest)
    {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.updateCustomerAddress(username,addressDto,id);

    }
    @ApiOperation("To update customer password")
    @PutMapping("/customer/update/password")
    public String updatePassword(@RequestBody PasswordDto passwordDto){
        customerService.updateCustomerPassword(passwordDto);
        return "Password updated...";
    }
    @GetMapping(value="/register/activate",produces = "application/json")
    public String activateCustomer(@RequestParam("token")String token)
    {
        return customerService.activateCustomer(token);
    }
    @DeleteMapping("/customer/addresses/{id}")
    public ResponseEntity deleteAddressById(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.deleteAddress(username, id);
    }

}
