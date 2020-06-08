package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StatusRepository extends CrudRepository<Status, Long> {
    @Query(value = "Select statusRepo from Status where report_id =:report_id",nativeQuery = true)
  public Status getStatusRepo(@Param("report_id")Long report_id);


}
