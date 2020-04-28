package com.ecommerceApp.ecommerceApp.Repositories;


import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Long> {
    @Query(value = "from Users where email=:email")
    Users findByEmail(@Param("email") String email);

    Optional<Users> findById(Long id);
        @Transactional
    @Modifying
    @Query(value = "update Users set password=:Password where email=:email")
    void updatePassword(@Param("Password") String password, @Param("email") String email);
}