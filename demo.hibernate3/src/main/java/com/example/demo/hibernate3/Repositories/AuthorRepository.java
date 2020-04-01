package com.example.demo.hibernate3.Repositories;

import com.example.demo.hibernate3.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Integer> {
}
