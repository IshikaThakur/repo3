package com.ecommerceApp.ecommerceApp.scheduler;

import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ReportRepository;
import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Report;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import com.ecommerceApp.ecommerceApp.services.ProductService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Component

@DisallowConcurrentExecution
public class ProductScheduler  {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(ProductScheduler.class);


    @Transactional
    public void execute(JobExecutionContext context) throws JobExecutionException {

    /* List<Object[]> products = productRepository.getProducts();
      for (Object[] values : products) {
          // System.out.println(values[0].toString() + " " + values[1].toString());
          Report report = new Report();
          report.setProductname(values[0].toString());
          report.setSellername(values[1].toString());
          report.setBrand(values[2].toString());
          report.setCategoryName(values[3].toString());
          reportRepository.save(report);
    /*  List<Users> list = userRepository.findByisActive(true);
      for (Users user : list) {
          System.out.println(user);
      }*/




        System.out.println("hiiiiiiiiiiiiiiiiii");
    }
}






