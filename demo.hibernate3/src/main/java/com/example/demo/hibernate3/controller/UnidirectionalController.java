package com.example.demo.hibernate3.controller;

import com.example.demo.hibernate3.Repositories.UnidirectionalRepository;
import com.example.demo.hibernate3.dao.UnidirectionalDao;
import com.example.demo.hibernate3.model.AuthorUnidirectional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnidirectionalController  {
    @Autowired
    UnidirectionalDao unidirectionalDao;
    @Autowired
    UnidirectionalRepository unidirectionalRepository;

    @GetMapping("/unidata")
    String setData(){
        AuthorUnidirectional authorUnidirectional=unidirectionalDao.setData();
        unidirectionalRepository.save(authorUnidirectional);
        return "unidirectional data set";
    }
}
