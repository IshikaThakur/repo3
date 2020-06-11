package com.ecommerceApp.ecommerceApp.mongoDb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoProductRepository extends MongoRepository<MongoProduct,Long>
{

public List<MongoProduct> findProductByName(String name);

}
