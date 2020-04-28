package com.ecommerceApp.ecommerceApp.security;

import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.exceptions.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
    @Autowired
    UserRepository userRepository;

    AppUser loadUserByUsername(String email) {
        Users user = userRepository.findByEmail(email);
        if (user != null) {
            return new AppUser(user);
        } else {
            throw new EmailAlreadyExistsException("user  " + user.getEmail() + " was not found");
        }
    }

}
