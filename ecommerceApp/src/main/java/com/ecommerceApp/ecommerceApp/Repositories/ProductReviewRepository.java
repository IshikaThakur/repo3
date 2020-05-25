package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.ProductReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductReviewRepository extends CrudRepository<ProductReview,Long> {
    List<ProductReview> findByProductId(Long id);
}
