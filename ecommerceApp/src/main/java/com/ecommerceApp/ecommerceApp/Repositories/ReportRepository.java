package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Long> {

    List<Report> findAll();
    @Query(value = "Select brand,categoryName,productname,sellername from Report where report_id = :report_id",nativeQuery = true)
    public List generateReport(@Param("report_id")Long report_id);

    @Query(value = "select brand,productname,categoryName,sellername from Report where report_id =:report_id",nativeQuery = true)
    public List findAllPros(@Param("report_id")Long report_id);



}
