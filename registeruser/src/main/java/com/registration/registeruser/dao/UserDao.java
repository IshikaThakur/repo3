package com.registration.registeruser.dao;


import com.registration.registeruser.entity.Role;
import com.registration.registeruser.entity.User;
import com.registration.registeruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class UserDao {
    @Autowired
    UserRepository userRepository;
    public AppUser loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        Set<Role> roles = user.getRoles();
        Iterator<Role> roleIterator = roles.iterator();
        String ro = null;
        List<GrantAuthorityImpl> list = new ArrayList<>();

        if (username != null) {
            return new AppUser(user.getUsername(), user.getPassword(), list);
        } else {
            throw new RuntimeException();
        }
    }
}