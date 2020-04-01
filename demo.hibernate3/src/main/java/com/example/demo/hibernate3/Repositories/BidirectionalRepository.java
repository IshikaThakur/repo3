package com.example.demo.hibernate3.Repositories;

import com.example.demo.hibernate3.model.AuthorBidirectional;
import org.springframework.data.repository.CrudRepository;

public interface BidirectionalRepository extends CrudRepository<AuthorBidirectional, Integer> {
}
