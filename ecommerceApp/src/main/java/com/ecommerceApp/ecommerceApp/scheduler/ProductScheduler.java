package com.ecommerceApp.ecommerceApp.scheduler;

import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductVariationRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.services.CurrentUserService;
import com.ecommerceApp.ecommerceApp.services.EmailSenderService;
import io.swagger.annotations.ApiOperation;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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
public class ProductScheduler implements Job {
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

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<Object[]> products = productRepository.getProducts();
        for (Object[] values : products) {
            System.out.println(values[0].toString() + " " + values[1].toString());
          //  System.out.println("Report Making has begun....You may want to add some other detail that is required to do the feature");

        }
    }
}








