package com.example.demo.hibernate3.controller;

import com.example.demo.hibernate3.dao.ManyToManyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManyToManyController {
    @Autowired
    ManyToManyDao manyToManyDao;
    @GetMapping("/manyToManyRelationship")
    void setData()
    {
        manyToManyDao.setData();
    }
}

