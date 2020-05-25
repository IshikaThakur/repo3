package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductReviewRepository;
import com.ecommerceApp.ecommerceApp.dtos.AddressDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductReviewDto;
import com.ecommerceApp.ecommerceApp.entities.Address;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.ProductReview;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewService {
    @Autowired
    ProductReviewRepository productReviewRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProductRepository productRepository;

    public ProductReviewDto toProductReviewDto(ProductReview productReview){
        if(productReview != null)
            return modelMapper.map(productReview, ProductReviewDto.class);
        return null;
    }

    public ProductReview toProductReview(ProductReviewDto productReviewDto){
        if(productReviewDto != null)
            return modelMapper.map(productReviewDto, ProductReview.class);
        return null;
    }



    public  String addProductReview(ProductReviewDto productReviewDto, String username) {


        Product product = productRepository.findById(productReviewDto.getProductId()).get();
        if (product == null)
            return "Not Found";

        ProductReview productReview = toProductReview(productReviewDto);
        Customer customer = customerRepository.findByEmail(username);

        productReview.setCustomer(customer);
        productReview.setRating(productReviewDto.getRating());
        productReview.setReview(productReviewDto.getReview());
        productReview.setProduct(product);
        productReviewRepository.save(productReview);



        return "Success";
    }

    public ResponseEntity getProductReviewByProductId(Long id, String username) {

        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            return new ResponseEntity("Not Found",HttpStatus.OK);
        }
        List<ProductReview> productReview = productReviewRepository.findByProductId(id);
        if (productReview == null)
            return new ResponseEntity("Not Found",HttpStatus.OK);


        List<ProductReviewDto> reviewDtos = new ArrayList<>();
        productReview.forEach(productReview1 -> {
            ProductReviewDto productReviewDto = toProductReviewDto(productReview1);
            reviewDtos.add(productReviewDto);
        });
        return new ResponseEntity(reviewDtos,HttpStatus.OK);

    }
}
