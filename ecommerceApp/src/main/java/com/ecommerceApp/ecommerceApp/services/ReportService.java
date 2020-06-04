package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ReportRepository;
import com.ecommerceApp.ecommerceApp.dtos.AddressDto;
import com.ecommerceApp.ecommerceApp.entities.*;
import com.ecommerceApp.ecommerceApp.scheduler.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    public void generateReport() {
        Date date = new Date();
        Tasks tasks = new Tasks();
        List<Object[]> productList = productRepository.getProducts();
        List<Product> products = (List<Product>) productRepository.findAll();
        for (Product product : products) {
            if (product.getCreatedTime() == date) {
                productService.getRep();
            }
        }
    }

    public Optional<Report> displayReport(Long id) {
        Optional<Report> report = productRepository.getReportById(id);
        if (report.get().getStatus() == 1) {
            return productRepository.getReportById(id);
        }
        return report;
    }
    public ResponseEntity<String> addNewProduct() {
        List<Product> products=productRepository.findByName();
        for(Product product:products) {
            Report newReport = new Report();
            newReport.addProduct(product);
            productRepository.save(product);
        }
        String message = "Product added successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }


}







