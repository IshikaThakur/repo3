package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Cart;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {
    List<Cart> findAll();

    @Query(value = "select * from cart where customer_user_id = :cart_id", nativeQuery = true)
    List<Cart> findCartByUserId(@Param("cart_id") Long cart_id);
}
