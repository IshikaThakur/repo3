package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.CartDto;
import com.ecommerceApp.ecommerceApp.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("/customer/add/cart")
    public ResponseEntity addCartItem(@Valid @RequestBody CartDto cartDto, HttpServletRequest httpServletRequest)
    {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        cartService.addToCart(cartDto,username);
       return new ResponseEntity("added", HttpStatus.OK);

    }

}
