package com.ecommerceApp.ecommerceApp.mongoDb;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Document(collection = "productvariation")
@Data
@AllArgsConstructor
public class MongoProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantityAvailable;
    private Double price;
    private Map<String, String> productAttributes;
}
