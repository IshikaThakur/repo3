package com.example.demo.hibernate3.dao;


import com.example.demo.hibernate3.Repositories.ManyToManyRepository;
import com.example.demo.hibernate3.model.AuthorManyToMany;
import com.example.demo.hibernate3.model.BookManyToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ManyToManyDao
{
    @Autowired
    ManyToManyRepository repository;
    public void setData()
    {
        AuthorManyToMany authorManyToMany1 = new AuthorManyToMany();
        authorManyToMany1.setAuthorName("Shakespeare");

        BookManyToMany bookManyToMany1 = new BookManyToMany();
        BookManyToMany bookManyToMany2 = new BookManyToMany();
        bookManyToMany1.setBookName("As YOu Like It");
        bookManyToMany2.setBookName("Much Ado About Nothing");
        Set<BookManyToMany> bookManyToManyHashSet = new HashSet<>();
        bookManyToManyHashSet.add(bookManyToMany1);
        bookManyToManyHashSet.add(bookManyToMany2);
        authorManyToMany1.setBookManyToManySet(bookManyToManyHashSet);


        AuthorManyToMany authorManyToMany2 = new AuthorManyToMany();
        authorManyToMany2.setAuthorName("Devdatt Pattanaik ");
        Set<AuthorManyToMany> authorManyToManyHashSet = new HashSet<>();
        authorManyToManyHashSet.add(authorManyToMany1);
        authorManyToManyHashSet.add(authorManyToMany2);
        bookManyToMany1.setAuthorManytoManySet(authorManyToManyHashSet);
        repository.save(authorManyToMany1);
        repository.save(authorManyToMany2);






    }


}
