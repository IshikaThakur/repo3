package com.registration.registeruser.repository;

import com.registration.registeruser.entity.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SellerRepository extends CrudRepository<Seller,Long>, PagingAndSortingRepository<Seller,Long> {
    Long findByGst(Long GST);
    Long findByCompanyContact(Long COMPANY_CONTACT);
   String findByCompanyName(String COMPANY_NAME);

   Seller findByUsername(String username);
}
