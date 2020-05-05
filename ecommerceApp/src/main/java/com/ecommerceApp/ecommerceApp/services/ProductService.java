package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CategoryRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductAdminDto;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductCustomerDto;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductSellerDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    SellerService sellerService;

    public Product toProduct(ProductSellerDto productSellerDto) {
        if (productSellerDto == null)
            return null;
        return modelMapper.map(productSellerDto, Product.class);
    }

    public ProductSellerDto toProductSellerDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductSellerDto.class);
    }

    public Product toProduct(ProductCustomerDto productCustomerDto) {
        if (productCustomerDto == null)
            return null;
        return modelMapper.map(productCustomerDto, Product.class);
    }

    public ProductCustomerDto toproductCustomerDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductCustomerDto.class);
    }

    public Product toProduct(ProductAdminDto productAdminDto) {
        if (productAdminDto == null)
            return null;
        return modelMapper.map(productAdminDto, Product.class);
    }

    public ProductAdminDto toProductAdminDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductAdminDto.class);
    }
  //====================API to add a new product=================================
    public ResponseEntity<String> addProduct(String email, ProductSellerDto productSellerDto) {

        Category category = categoryRepository.findById(productSellerDto.getCategoryId()).get();
        Seller seller = sellerRepository.findByEmail(email);
        Product product = toProduct(productSellerDto);
        product.setCategory(category);
        product.setSeller(seller);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(seller.getEmail());
        mailMessage.setSubject("Information about the new product created");
        mailMessage.setFrom("tanu.thakur0816@gmail.com");
        mailMessage.setText("A product with following details has been created - \n" +
                "name - " + product.getName() + "\n" +
                "category - " + product.getCategory().getName() + "\n" +
                "brand - " + product.getBrand() + "\n" +
                "description - " + product.getDescription());

        emailSenderService.sendEmail(mailMessage);
        productRepository.save(product);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

}
