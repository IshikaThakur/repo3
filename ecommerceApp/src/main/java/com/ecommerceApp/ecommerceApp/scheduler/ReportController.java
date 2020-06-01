package com.ecommerceApp.ecommerceApp.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
@Autowired
    Tasks tasks;
    @RequestMapping(value = "product/get", method = RequestMethod.GET)
    public ResponseEntity start() throws Exception {
        tasks.work();
        return new ResponseEntity("Your task is in progress", HttpStatus.OK);
    }

}
