package com.ecommerceApp.ecommerceApp.mongoDb;



import com.ecommerceApp.ecommerceApp.entities.ProductReview;
import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Document(collection = "product")
@Data
@AllArgsConstructor
public class MongoProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String brand;
    private List<ProductReview> reviews;
    private List<ProductVariation> variations;


}