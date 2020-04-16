package com.registration.registeruser.repository;

import com.registration.registeruser.entity.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);

}
