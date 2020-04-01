package com.example.demo.hibernate3.controller;



import com.example.demo.hibernate3.Repositories.AuthorRepository;
import com.example.demo.hibernate3.dao.AuthorDao;
import com.example.demo.hibernate3.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class AuthorController
{
    @Autowired
    AuthorRepository repository;
    @Autowired
    AuthorDao authorDao;
    @GetMapping("/saveAuthor")
    String saveAuthor()
    {
        Author author=authorDao.authorData();
        repository.save(author);
        return "Record added sucessfully";
    }
}
