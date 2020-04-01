package com.example.demo.hibernate3.Repositories;

import com.example.demo.hibernate3.model.AuthorWithoutExtraTable;
import org.springframework.data.repository.CrudRepository;

public interface WithoutExtraTableRepository extends CrudRepository<AuthorWithoutExtraTable, Integer> {
}
