package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.VerificationToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken,Long> {
    @Query(value = "select *from VerificationToken where token=:token",nativeQuery = true)
    VerificationToken findByToken(@Param("token") String token);
}
