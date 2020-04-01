package com.example.demo.hibernate3.dao;

import com.example.demo.hibernate3.model.AuthorBidirectional;
import com.example.demo.hibernate3.model.BookBidirectional;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BidirectionalDao {
    public AuthorBidirectional setData(){
        AuthorBidirectional authorBidirectional=new AuthorBidirectional();
        authorBidirectional.setName("Ishika");
        BookBidirectional bookBidirectional=new BookBidirectional();
        BookBidirectional bookBidirectional1=new BookBidirectional();
        bookBidirectional.setBookName("book12");
        bookBidirectional1.setBookName("book23");
        Set<BookBidirectional> bookBidirectionalSet=new HashSet<>();
        bookBidirectionalSet.add(bookBidirectional);
        bookBidirectionalSet.add(bookBidirectional1);
        authorBidirectional.setBookBidirectional(bookBidirectionalSet);
        return authorBidirectional;
    }
}
