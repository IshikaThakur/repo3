package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String getCurrentLoggedInUser(){
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if(principal instanceof UserDetails)
        {
            username=((UserDetails)principal).getUsername();

        }
        else
        {
            username=principal.toString();
        }
        return username;
        }
}
