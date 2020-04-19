package com.registration.registeruser.repository;

import com.registration.registeruser.entity.TokenGenerator;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<TokenGenerator,Long> {
}
