package com.example.demo.hibernate3.controller;

import com.example.demo.hibernate3.Repositories.WithoutExtraTableRepository;
import com.example.demo.hibernate3.dao.WithOutExtraTableDao;
import com.example.demo.hibernate3.model.AuthorWithoutExtraTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithoutExtraTableController {
    @Autowired
    WithOutExtraTableDao withOutExtraTableDao;
    @Autowired
    WithoutExtraTableRepository withoutExtraTableRepository;

    @GetMapping("/notabledata")
    String setData(){
        AuthorWithoutExtraTable authorWithoutExtraTable=withOutExtraTableDao.setData();
        withoutExtraTableRepository.save(authorWithoutExtraTable);
        return "data set without creating extra data";
    }

}

