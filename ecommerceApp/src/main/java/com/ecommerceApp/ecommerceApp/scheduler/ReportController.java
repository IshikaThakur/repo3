package com.ecommerceApp.ecommerceApp.scheduler;

import com.ecommerceApp.ecommerceApp.Repositories.ReportRepository;
import com.ecommerceApp.ecommerceApp.entities.Report;
import com.ecommerceApp.ecommerceApp.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {
@Autowired
    Tasks tasks;
@Autowired
    ReportRepository reportRepository;
@Autowired
    ReportService reportService;
    @RequestMapping(value = "product/get", method = RequestMethod.GET)
    public ResponseEntity start() throws Exception {
      tasks.work();
        return new ResponseEntity("Your task is in progress", HttpStatus.OK);
    }
    @GetMapping(value = "product/gets/{report_id}")
    public List generateReport(@PathVariable Long report_id) {
        return reportRepository.generateReport(report_id);
    }


}
