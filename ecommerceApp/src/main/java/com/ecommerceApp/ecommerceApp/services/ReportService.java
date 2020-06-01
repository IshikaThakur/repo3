package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.ReportRepository;
import com.ecommerceApp.ecommerceApp.entities.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    public ResponseEntity<Report> getData(Long id) throws InterruptedException {
    Optional<Report> report = reportRepository.getReportById(id);
    Report report1 = new Report();
    if (reportRepository.getReportById(id).get().getStatus() == 1) {
        return new ResponseEntity(report, HttpStatus.OK);
    } else
        return new ResponseEntity("Wait....Your report is being generated..", HttpStatus.OK);
}
}