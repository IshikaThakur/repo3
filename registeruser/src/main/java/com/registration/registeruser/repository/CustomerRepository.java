package com.registration.registeruser.repository;

import com.registration.registeruser.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long>, PagingAndSortingRepository<Customer,Long> {
 //   String findByEmail(String email);
  Customer findByUsername(String username);
//    List<Customer>findAll();
    Customer findByEmail(String email);
    List<Customer>findAll();
    Page<Customer> findAll(Pageable pageable);
}
