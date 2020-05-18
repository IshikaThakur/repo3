package com.ecommerceApp.ecommerceApp.scheduler;

import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductVariationRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ReportRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Report;
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
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
    ProductRepository productRepository;
    @Autowired
    ReportRepository reportRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductScheduler.class);
   @Transactional
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
            List<Object[]> products = productRepository.getProducts();
            for (Object[] values : products) {
                // System.out.println(values[0].toString() + " " + values[1].toString());
                Report report = new Report();
                report.setProductname(values[0].toString());
                report.setSellername(values[1].toString());
                report.setBrand(values[2].toString());
                report.setCategoryName(values[3].toString());
                reportRepository.save(report);


            }
        }

    }







