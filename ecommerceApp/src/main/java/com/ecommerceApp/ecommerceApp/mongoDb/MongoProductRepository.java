package com.ecommerceApp.ecommerceApp.mongoDb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoProductRepository extends MongoRepository<MongoProduct,Long>
{


}
