package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductVariationRepository;
import com.ecommerceApp.ecommerceApp.dtos.ProductVariationSellerDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductVariationUpdateDto;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductSellerDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductVariationService {
    @Autowired
    ProductVariationRepository productVariationRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryService categoryService;

    public ProductVariation toProductVariation(ProductVariationSellerDto variationDto) {
        if (variationDto == null)
            return null;
        return modelMapper.map(variationDto, ProductVariation.class);
    }

    public ProductVariationSellerDto toProductVariationSellerDto(ProductVariation variation) {
        if (variation == null)
            return null;
        return modelMapper.map(variation, ProductVariationSellerDto.class);
    }

    public ProductSellerDto toProductSellerDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductSellerDto.class);
    }

    public String validateNewProductVariation(String email, ProductVariationSellerDto variationDto) {

        String message;

        Optional<Product> savedProduct = productRepository.findById(variationDto.getProductId());
        if (!savedProduct.isPresent()) {
            message = "Parent product does not exist.";
            return message;
        }

        Product parentProduct = savedProduct.get();
        Category category = parentProduct.getCategory();
        Map<String, String> attributes = variationDto.getAttributes();


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
        if (variationDto.getQuantityAvailable() <= 0) {
            message = "Quantity should be greater than 0.";
            return message;
        }
        if (variationDto.getPrice() <= 0) {
            message = "Price should be greater than 0";
            return message;
        }

        return "success";
    }

    public ResponseEntity saveNewProductVariation(String email, ProductVariationSellerDto variationDto) {
       /* if(variationDto.getQuantityAvailable()<=0||variationDto.getPrice()<=0)

        String message = validateNewProductVariation(email, variationDto);
       if (!message.equalsIgnoreCase("success")) {
            return new ResponseEntity("Enter the fields again", HttpStatus.BAD_REQUEST);
        }*/
        if (variationDto.getPrice() == null || variationDto.getProductId() == null || variationDto.getQuantityAvailable() == null) {
            return new ResponseEntity("Fields canot be null", HttpStatus.BAD_REQUEST);
        } else {
            ProductVariation newVariation = toProductVariation(variationDto);
            productVariationRepository.save(newVariation);
            return new ResponseEntity("Success", HttpStatus.CREATED);
        }
    }

    public ResponseEntity getProductVariationByIdForSeller(String email, Long id) {

        String message;

        Optional<ProductVariation> savedVariation = productVariationRepository.findById(id);
        if (!savedVariation.isPresent()) {

            return new ResponseEntity("Success", HttpStatus.NOT_FOUND);
        }
        ProductVariation variation = savedVariation.get();
        if (!variation.getProduct().getSeller().getEmail().equalsIgnoreCase(email)) {
            message = "Product variation with id " + id + " does not belong to you.";
            return new ResponseEntity("Success", HttpStatus.BAD_REQUEST);
        }
        if (variation.isDeleted()) {
            message = "Product Variation does not exist.";
            return new ResponseEntity("does not exist", HttpStatus.BAD_REQUEST);
        }

        ProductVariationSellerDto variationDto = toProductVariationSellerDto(variation);
        ProductSellerDto productDto = toProductSellerDto(variation.getProduct());
        productDto.setCategoryDto(categoryService.toCategoryDto(variation.getProduct().getCategory()));
        variationDto.setProductDto(productDto);


        return new ResponseEntity(variationDto, HttpStatus.OK);
    }

    //===================Get All===========
    public ResponseEntity getAllProductVariationsByProductIdForSeller(String email, Long id, String offset, String size, String sortByField, String order) {
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            return new ResponseEntity("Not found,Invalid", HttpStatus.NOT_FOUND);
        }
        Product product = savedProduct.get();
        if (!product.getSeller().getEmail().equalsIgnoreCase(email)) {
            return new ResponseEntity("Id does not belong to you", HttpStatus.BAD_REQUEST);
        }
        if (product.isDeleted()) {
            return new ResponseEntity("Product does not exists", HttpStatus.BAD_REQUEST);
        }

        //  Pageable pageable = pagingService.getPageableObject(offset, size, sortByField, order);

        List<ProductVariation> variations;
        variations = productVariationRepository.findByProductId(id);

        List<ProductVariationSellerDto> variationDtos = new ArrayList<>();
        variations.forEach(variation -> {
            ProductVariationSellerDto variationDto = toProductVariationSellerDto(variation);
            ProductSellerDto productDto = toProductSellerDto(variation.getProduct());
            productDto.setCategoryDto(categoryService.toCategoryDto(variation.getProduct().getCategory()));
            variationDto.setProductDto(productDto);
            variationDtos.add(variationDto);
        });
        return new ResponseEntity(variationDtos, HttpStatus.OK);
    }

    //================Updation=========================
    public ResponseEntity validateProductVariationUpdate(Long id, String email, ProductVariationUpdateDto variationDto) {
        String message;

        Optional<ProductVariation> savedVariation = productVariationRepository.findById(id);
        if (!savedVariation.isPresent()) {
            message = "Product variation with id " + id + " not found";
            return new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }

        ProductVariation variation = savedVariation.get();

        if (variation.isDeleted()) {
            message = "Product variation does not exist.";
            return new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }
        if (!variation.getProduct().isActive()) {
            message = "Parent product is inactive.";
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
        if (!variation.getProduct().getSeller().getEmail().equalsIgnoreCase(email)) {
            message = "Product variation does not belong to you.";
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);

        }
        if (variationDto.getQuantityAvailable() != null && variationDto.getQuantityAvailable() <= 0) {
            message = "Quantity should be greater than 0.";
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
        if (variationDto.getPrice() != null && variationDto.getPrice() <= 0) {
            message = "Price should be greater than 0";
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }


        return null;
    }

    public ResponseEntity updateProductVariationById(Long id, String email, ProductVariationUpdateDto variationDto) {
        String message;

        ResponseEntity validationResponse = validateProductVariationUpdate(id, email, variationDto);
       if (validationResponse != null) {
           return validationResponse;
       }
      else  if(variationDto.getAttributes()==null||variationDto.getQuantityAvailable()==null||variationDto.getPrice()==null)
        {
            return new ResponseEntity("Fields cannot be empty,Please Check again",HttpStatus.BAD_REQUEST);
        }
        else {

            ProductVariation variation = productVariationRepository.findById(id).get();

            // now we can save the product variation.
            applyProductVariationUpdateDtoToProductVariation(variation, variationDto);
            productVariationRepository.save(variation);

            message = "success";
            return new ResponseEntity(message, HttpStatus.OK);
        }

    }

    private void applyProductVariationUpdateDtoToProductVariation(ProductVariation variation, ProductVariationUpdateDto variationDto) {

        if (variationDto.getQuantityAvailable() != null)
            variation.setQuantityAvailable(variationDto.getQuantityAvailable());

        if (variationDto.getPrice() != null)
            variation.setPrice(variationDto.getPrice());

        if (variationDto.getActive() != null)
            variation.setActive(variationDto.getActive());

        if (variationDto.getAttributes() != null) {
            Map<String, String> newAttributes = variationDto.getAttributes();
            if (!newAttributes.isEmpty()) {
                Map<String, String> oldAttributes = variation.getProductAttributes();

                for (String key : newAttributes.keySet()) {
                    String newValue = newAttributes.get(key);
                    oldAttributes.put(key, newValue);
                }
            }
        }
    }

}
