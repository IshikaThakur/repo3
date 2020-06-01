package com.ecommerceApp.ecommerceApp.scheduler;

import com.ecommerceApp.ecommerceApp.Repositories.ReportRepository;
import com.ecommerceApp.ecommerceApp.entities.Report;
import com.ecommerceApp.ecommerceApp.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

@GetMapping(value = "product/gets/{id}")
    public <Optional> ResponseEntity<Report> getDetail(@PathVariable Long id) throws InterruptedException {
    return reportService.getData(id);
}

}
