package com.nativeandjpqlassign.nativeandjpql.repos;

import com.nativeandjpqlassign.nativeandjpql.model.EmployeeEmbedded;
import com.nativeandjpqlassign.nativeandjpql.model.Salary;
import org.springframework.data.repository.CrudRepository;

public interface EmbeddedRepository extends CrudRepository<EmployeeEmbedded,Integer> {
}
