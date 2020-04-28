package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query(value = "select * from Product where id=:productId and seller_user_id=:userId", nativeQuery = true)
    Optional<Product> findByIdAndSellerId(@Param("userId") Long userid, @Param("productId") Long productId);

    @Query(value = "select * from Product where seller_user_id=:userId", nativeQuery = true)
    List<Product> findAllBySeller(@Param("userId") Long userId , Pageable pageable);
}
