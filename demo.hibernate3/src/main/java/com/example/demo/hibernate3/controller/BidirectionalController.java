package com.example.demo.hibernate3.controller;


import com.example.demo.hibernate3.Repositories.BidirectionalRepository;
import com.example.demo.hibernate3.dao.BidirectionalDao;
import com.example.demo.hibernate3.model.AuthorBidirectional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidirectionalController {
    @Autowired
    BidirectionalDao bidirectionalDao;
    @Autowired
    BidirectionalRepository bidirectionalRepository;

    @GetMapping("/bidata")
    String setData(){
        AuthorBidirectional authorBidirectional=bidirectionalDao.setData();
        bidirectionalRepository.save(authorBidirectional);
        return "bidirectional data set";
    }

}
