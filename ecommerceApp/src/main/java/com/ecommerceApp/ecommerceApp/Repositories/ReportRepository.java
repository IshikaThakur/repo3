package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends CrudRepository<Report, Long> {

    List<Report> findAll();

    @Query(value = "select * from Report where id =:id and createdDate BETWEEN '09:00:00' AND '23:59:00'", nativeQuery = true)
    Optional<Report> getReportById(@Param("id")Long id);
}
