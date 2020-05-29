package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.rabbitMQ.AMQPProducer;
import com.ecommerceApp.ecommerceApp.services.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class OrdersController {
    @Autowired
    OrderService orderService;
    @Autowired
    AMQPProducer amqpProducer;

    @ApiOperation("to place order")
    @GetMapping("/customer/placeOrder/{productVariationId}/{quantity}/{paymentMethod}/{AddressId}")
    ResponseEntity placeOrder(@PathVariable Long productVariationId, @PathVariable int quantity,
                                     @PathVariable String paymentMethod, @PathVariable Long AddressId, HttpServletRequest httpServletRequest){
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
       return orderService.placeOrder(productVariationId, quantity, paymentMethod, AddressId,username);
      //  return new ResponseEntity("**********ORDER PLACED********", HttpStatus.OK);
    }
}
