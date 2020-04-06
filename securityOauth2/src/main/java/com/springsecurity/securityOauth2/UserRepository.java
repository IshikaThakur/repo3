package com.springsecurity.securityOauth2;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends JpaRepository<User,Integer> {

User findByUsername(String username);
}
