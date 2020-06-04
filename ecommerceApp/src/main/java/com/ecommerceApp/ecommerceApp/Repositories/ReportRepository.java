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
    @Query(value = "Select brand,categoryName,productname,sellername from Report where report_id = :report_id",nativeQuery = true)
    public List generateReport(@Param("report_id")Long report_id);



}
