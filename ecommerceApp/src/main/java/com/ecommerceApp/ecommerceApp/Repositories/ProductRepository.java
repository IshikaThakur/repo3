package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product,Long> {

    List<Product> findAllBySeller(Long id,Pageable pageable);

    Optional<Product> findByIdAndSellerId(Long productId, Long id);

}
