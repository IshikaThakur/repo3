package com.ecommerceApp.ecommerceApp.scheduler;

import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ReportRepository;
import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.dtos.CustomerDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Report;
import com.ecommerceApp.ecommerceApp.entities.Users;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ActivateScheduler {
    @Autowired
    UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(ActivateScheduler.class);
    private static final DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReportRepository reportRepository;


    @Scheduled(fixedRate = 40000)
    @Transactional
    public void getPro() {
        List<Object[]> products = productRepository.getProducts();
        for (Object[] values : products) {
            // System.out.println(values[0].toString() + " " + values[1].toString());
            Report report=new Report();
            report.setProductname(values[0].toString());
            report.setSellername(values[1].toString());
            report.setBrand(values[2].toString());
           report.setCategoryName(values[3].toString());
            reportRepository.save(report);





  /*  public void unlockAccount() {
        List<Users> list = userRepository.findByisActive(false);
        for (Users user : list) {
            user.setActive(true);
            userRepository.save(user);
        }*/

        }
    }}
