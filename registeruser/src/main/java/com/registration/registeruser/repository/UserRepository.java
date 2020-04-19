package com.registration.registeruser.repository;

import com.registration.registeruser.entity.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends CrudRepository<User,Long>, PagingAndSortingRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByPassword(String password);
}
