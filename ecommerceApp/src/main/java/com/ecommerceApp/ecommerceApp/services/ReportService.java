package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.ReportRepository;
import com.ecommerceApp.ecommerceApp.entities.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    public ResponseEntity<Report> getData()  {
        List<Report> report = reportRepository.findAll();
        return new ResponseEntity(report,HttpStatus.OK);

    }}
