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


        List<Object[]> products = productRepository.getProducts();
        Status status = new Status();
        for (Object[] values : products) {
            Report report = new Report();
            report.setProductname(values[0].toString());
            report.setSellername(values[1].toString());
            report.setBrand(values[2].toString());
            report.setCategoryName(values[3].toString());
            report.setReport_id(status.getReport_id());
            status.setStatusRepo(1);
            reportRepository.save(report);
            statusRepository.save(status);
        

        }

    }
    public List<Report> getReports(Long report_id) {
        Status status = statusRepository.getStatusRepo(report_id);
        List<Report>reportList=null;
        if (status.getReport_id() == 1) {
        reportList = reportRepository.generateReport(report_id);
        }
        return reportList;

    }


}




