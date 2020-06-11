package com.ecommerceApp.ecommerceApp.mongoDb;

import com.ecommerceApp.ecommerceApp.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Set;



@Data
@AllArgsConstructor
@Document(collection = "seller")
public class MongoSeller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String GST;
    private String companyName;
    private String companyContact;
    private String email;
    private List<Product> products;


}
