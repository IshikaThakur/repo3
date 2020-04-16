package com.registration.registeruser.repository;

import com.registration.registeruser.entity.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token,Long> {
}
