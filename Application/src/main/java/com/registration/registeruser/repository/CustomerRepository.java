package com.registration.registeruser.repository;

import com.registration.registeruser.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long>, PagingAndSortingRepository<Customer,Long> {
    String findByEmail(String email);
    Customer findByUsername(String username);
}
