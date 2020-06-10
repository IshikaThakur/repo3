package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ReportRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.StatusRepository;
import com.ecommerceApp.ecommerceApp.entities.Report;
import com.ecommerceApp.ecommerceApp.entities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    StatusRepository statusRepository;



    @Scheduled(cron = "0 */2 * ? * *")
    public void getRep() {
        Status status = new Status();
        status.setStatusRepo(1);
        List<Object[]> products = productRepository.getProducts();


        for (Object[] values : products) {
            Report report = new Report();
            report.setReport_id(status.getReport_id());
            report.setProductname(values[0].toString());
            report.setSellername(values[1].toString());
            report.setBrand(values[2].toString());
            report.setCategoryName(values[3].toString());
            statusRepository.save(status);
            reportRepository.save(report);


        }

    }
    public List<Report> getReports(Long report_id) {
        List<Report> reports = reportRepository.findAllPros(report_id);
        List<Report> reports1 = new ArrayList<>();
        reports.forEach(report -> reports1.add(report));
        return reports1;


    }

}




