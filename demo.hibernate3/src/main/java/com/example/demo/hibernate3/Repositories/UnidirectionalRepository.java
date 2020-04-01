package com.example.demo.hibernate3.Repositories;

import com.example.demo.hibernate3.model.AuthorUnidirectional;
import org.springframework.data.repository.CrudRepository;

public interface UnidirectionalRepository extends CrudRepository<AuthorUnidirectional,Integer> {
}
