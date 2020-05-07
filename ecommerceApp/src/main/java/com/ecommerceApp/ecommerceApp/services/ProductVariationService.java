package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.*;
import com.ecommerceApp.ecommerceApp.dtos.BaseDto;
import com.ecommerceApp.ecommerceApp.dtos.ErrorDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductvariationSellerDto;
import com.ecommerceApp.ecommerceApp.dtos.ResponseDto;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductSellerDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import com.ecommerceApp.ecommerceApp.entities.category.CategoryMetadataField;
import com.google.common.collect.Sets;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProductVariationService {
    @Autowired
    ProductVariationRepository productVariationRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryMetadataFieldRepository categoryMetadataFieldRepository;
    @Autowired
    CategoryMetadaFieldValuesRepository categoryMetadaFieldValuesRepository;

    public ProductVariation toProductVariation(ProductvariationSellerDto variationDto) {
        if (variationDto == null)
            return null;
        return modelMapper.map(variationDto, ProductVariation.class);
    }

    public ProductvariationSellerDto
    toProductVariationSellerDto(ProductVariation variation) {
        if (variation == null)
            return null;
        return modelMapper.map(variation, ProductvariationSellerDto.class);
    }

    public Product toProduct(ProductSellerDto productDto) {
        if (productDto == null)
            return null;
        return modelMapper.map(productDto, Product.class);
    }



    public String validateNewProductVariation(String email, ProductvariationSellerDto productvariationSellerDto) {
        BaseDto response;
        String message;

        Optional<Product> savedProduct = productRepository.findById(productvariationSellerDto.getProductId());
        if (!savedProduct.isPresent()) {
            message = "Parent product does not exist.";
            return message;
        }

        Product parentProduct = savedProduct.get();
        Category category = parentProduct.getCategory();
        Map<String, String> metadata = productvariationSellerDto.getMetadata();


        if (parentProduct.isDeleted()) {
            message = "Parent product does not exist.";
            return message;
        }
        if (!parentProduct.isActive()) {
            message = "Parent product is inactive.";
            return message;
        }
        if (!parentProduct.getSeller().getEmail().equalsIgnoreCase(email)) {
            message = "Parent product does not belong to you.";
            return message;
        }
        if (productvariationSellerDto.getQuantity() <= 0) {
            message = "Quantity should be greater than 0.";
            return message;
        }
        if (productvariationSellerDto.getPrice() <= 0) {
            message = "Price should be greater than 0";
            return message;
        }

        // check if all the fields are actually related to the product category.
        List<String> receivedFields = new ArrayList<>(metadata.keySet());
        List<String> actualFields = new ArrayList<>();
        categoryMetadaFieldValuesRepository.findAllFieldsOfCategoryById(category.getId())
                .forEach((e) -> {
                    actualFields.add(e[0].toString());
                });

        if (receivedFields.size() < actualFields.size()) {
            message = "Please provide all the fields related to the product category.";
            return message;
        }

        receivedFields.removeAll(actualFields);
        if (!receivedFields.isEmpty()) {
            message = "Invalid fields found in the data.";
            return message;
        }
        List<String> receivedFieldsCopy = new ArrayList<>(metadata.keySet());


        return "success";
    }
   // ====Creation of new ProductVariation============================================
    public ResponseEntity<BaseDto> saveNewProductVariation(String email,ProductvariationSellerDto productvariationSellerDto) {


        ProductVariation newVariation = toProductVariation(productvariationSellerDto);
        productVariationRepository.save(newVariation);
        return new ResponseEntity("Sucess", HttpStatus.CREATED);
    }
   // ================== Updation of ProductVariationById====================


}