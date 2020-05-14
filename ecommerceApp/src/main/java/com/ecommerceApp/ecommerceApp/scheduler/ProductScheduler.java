package com.ecommerceApp.ecommerceApp.scheduler;

import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductVariationRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.services.CurrentUserService;
import com.ecommerceApp.ecommerceApp.services.EmailSenderService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractAuditable_.createdDate;

@Component
public class ProductScheduler {
    @Autowired
    ProductVariationRepository productVariationRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductScheduler.class);


  // @Scheduled(fixedRate = 60000)
  //@Scheduled(cron = " * 20 * * * ?")
    public void newProducts() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Iterable<Product> products = productRepository.findAllWithCreationDate();
        for (Product product : products) {
            System.out.println(product);
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("Product");
                simpleMailMessage.setText("A product with following details has been created - \n" +
                        "name - " + product.getName() + "\n" +
                        "brand - " + product.getBrand() + "\n" );
                simpleMailMessage.setFrom("tanu.thakur0816@gmail.com");
                simpleMailMessage.setTo("ishikathakur880@gmail.com");
                emailSenderService.sendEmail(simpleMailMessage);
            }
        }
    }





