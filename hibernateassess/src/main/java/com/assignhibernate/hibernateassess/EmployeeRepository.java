package com.assignhibernate.hibernateassess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Integer>, PagingAndSortingRepository<Employee,Integer>
{
    List<Employee> findByName(String name);
    List<Employee> findByNameLike(String s);
    List<Employee> findByAgeBetween(int i, int i1);


}
