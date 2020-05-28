package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductVariationRepository extends CrudRepository<ProductVariation,Long> {
    List<ProductVariation> findAll();
    List<ProductVariation> findAll(Pageable pageable);

    List<ProductVariation> findByProductId(Long id);
    List<ProductVariation> findByProductId(Long id, Pageable pageable);

    @Modifying
    @Transactional

    @Query(value = "delete from product_variation where product_id = :p_id", nativeQuery = true)
    void deleteByProductId(@Param("p_id") Long p_id);

    @Query(value = "select name from product where id in (select product_id from product_variation where quantity_available=:0) and seller_user_id=:id;);", nativeQuery = true)
    public List<Object[]> outOfStockProducts(@Param(value = "id") Long id);

    @Query(value = "select price from ProductVariation where id =:id",nativeQuery = true)
    public Long getPrice(@Param(value = "id") Long id);

    @Query(value = "select product_id from ProductVariation where id =:id",nativeQuery = true)
    public Long getProductId(@Param(value = "id") Long id);



}
