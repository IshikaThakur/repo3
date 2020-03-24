package com.nativeandjpqlassign.nativeandjpql.repos;

import com.nativeandjpqlassign.nativeandjpql.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment,Integer> {
}
