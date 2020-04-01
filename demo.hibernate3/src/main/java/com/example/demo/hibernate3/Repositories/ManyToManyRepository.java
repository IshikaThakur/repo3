package com.example.demo.hibernate3.Repositories;

import com.example.demo.hibernate3.model.AuthorManyToMany;
import org.springframework.data.repository.CrudRepository;

public interface ManyToManyRepository extends CrudRepository<AuthorManyToMany,Integer> {
}
