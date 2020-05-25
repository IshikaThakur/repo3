package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CartRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductVariationRepository;
import com.ecommerceApp.ecommerceApp.dtos.CartDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductReviewDto;
import com.ecommerceApp.ecommerceApp.entities.Cart;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.ProductReview;
import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class CartService {
    @Autowired
    ProductVariationRepository productVariationRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    ModelMapper modelMapper;

    public CartDto toCartDto(Cart cart){
        if(cart != null)
            return modelMapper.map(cart, CartDto.class);
        return null;
    }

    public Cart toCart(CartDto cartDto){
        if(cartDto != null)
            return modelMapper.map(cartDto, Cart.class);
        return null;
    }

    public ResponseEntity addToCart(CartDto cartDto, String username) {
        ProductVariation product = productVariationRepository.findById(cartDto.getProductVarId()).get();
        if (product == null)
           return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);

        Cart cart = toCart(cartDto);
        Customer customer = customerRepository.findByEmail(username);


        cart.setProductVariation(product);
        cart.setQuantity(cartDto.getQuantity());
        cart.setCustomer(customer);
        cartRepository.save(cart);
        return new ResponseEntity("Added", HttpStatus.OK);
    }
}
