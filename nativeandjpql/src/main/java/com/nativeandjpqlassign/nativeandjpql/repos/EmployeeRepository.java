package com.nativeandjpqlassign.nativeandjpql.repos;

import com.nativeandjpqlassign.nativeandjpql.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    @Query("select em.firstName,em.lastName from Employee em where salary>(select avg(salary)from Employee)")
    List<Object[]> findAllEmployeePartialData(Pageable pageable);
@Query("select AVG(salary) from Employee")
double avgSalary();

    @Transactional
    @Query("update Employee set salary=:newsalary  where salary<:avg ")
    @Modifying
    void updateSalary(@Param("newsalary") double newsalary,@Param("avg") double avg);

@Query("select min(salary) from Employee")
double minSalary();

@Modifying
@Query(value="delete from Employee where salary=:min")
    void deleteSalary(@Param("min") double min);

@Query(value = " select emp_id, emp_first_name, emp_age from employee where emp_last_name like '%Thakur' ",nativeQuery = true)
List<Object[]> findEmployeesWhoseLastNameEndsWithSingh();

@Modifying
@Query(value = "delete from employee where emp_age>:age",nativeQuery = true)
void deleteEmployeesWithAgeGreaterThan45(@Param("age") int age);

}
