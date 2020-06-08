package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Report;
import org.aspectj.weaver.patterns.Declare;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.keyvalue.repository.config.QueryCreatorType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import java.util.*;

public interface ProductRepository extends CrudRepository<Product,Long> {

    Product findByName(String name);

    List<Product> findAll(Pageable pageable);

    List<Product> findByBrandAndCategoryId(String brand, Long id, Pageable pageable);

    List<Product> findByBrand(String brand, Pageable pageable);

    Set<Product> findByBrand(Long categoryId);

    List<Product> findByCategoryId(Long id, Pageable pageable);


    @Modifying
    @Transactional
    @Query(value = "delete from Product where id = :p_id", nativeQuery = true)
    void deleteProductById(@Param("p_id") Long p_id);

    @Query(value = "select name,brand from Product where CURRENT_DATE = :(CAST(createdDate() AS date))", nativeQuery = true)
    List<Product> findAllWithCreationDate();

    @Query(value = "Select Product.name,Users.email,Product.brand,CATEGORY.name as category from Product INNER JOIN Users ON Product.seller_user_id =Users.id INNER JOIN CATEGORY ON Product.category_id =CATEGORY.id where cast(createdTime as date) =current_date() and cast(createdTime as time) BETWEEN '09:00:00' and '24:00:00'", nativeQuery = true)
    public List<Object[]> getProducts();


    @Query(value = "select id from Product where category_id=:category_id and brand=:brand", nativeQuery = true)
    List<Long> getIdOfSimilarProduct(@Param("category_id") Long id, @Param("brand") String brand, Pageable paging);

    @Query(value = "select category_id from Product where id=:id", nativeQuery = true)
    Long getCategoryId(@Param("id") Long productId);

    @Query(value = "select * from Product where id =:id and createdDate BETWEEN '09:00:00' AND '15:59:00'", nativeQuery = true)
    Optional<Report> getReportById(@Param("id") Long id);

    @Query(value = "select * from Product where cast(createdTime as date)=current_date and cast(createdTime as time) BETWEEN '11:00:00' and '24:00:00'", nativeQuery = true)
    List<Product> findAllProducts();


}