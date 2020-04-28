package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Seller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellerRepository extends CrudRepository<Seller,Long> {
    Seller findByEmail(String email);
    List<Seller> findAll();
    List<Seller> findAll(Pageable pageable);

    Seller findByGST(String gst);

    Seller findByCompanyName(String name);
}
