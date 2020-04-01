package com.example.demo.hibernate3.dao;

import com.example.demo.hibernate3.model.AuthorWithoutExtraTable;
import com.example.demo.hibernate3.model.BookWithoutExtraTable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class WithOutExtraTableDao {
    public AuthorWithoutExtraTable setData(){
        AuthorWithoutExtraTable authorWithoutExtraTable=new AuthorWithoutExtraTable();
        authorWithoutExtraTable.setName("Kim");
        BookWithoutExtraTable bookWithoutExtraTable=new BookWithoutExtraTable();
        BookWithoutExtraTable bookWithoutExtraTable1=new BookWithoutExtraTable();
        bookWithoutExtraTable.setBookName("book424");
        bookWithoutExtraTable1.setBookName("book123");
        Set<BookWithoutExtraTable> bookWithoutExtraTableSet=new HashSet<>();
        bookWithoutExtraTableSet.add(bookWithoutExtraTable);
        bookWithoutExtraTableSet.add(bookWithoutExtraTable1);
        authorWithoutExtraTable.setBookWithoutExtraTables(bookWithoutExtraTableSet);
        return authorWithoutExtraTable;
    }
}
