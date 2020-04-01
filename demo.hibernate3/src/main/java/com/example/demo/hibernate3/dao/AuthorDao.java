package com.example.demo.hibernate3.dao;

import com.example.demo.hibernate3.model.Author;
import com.example.demo.hibernate3.model.AuthorAddress;
import com.example.demo.hibernate3.model.Book;
import com.example.demo.hibernate3.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class AuthorDao
{
    public Author authorData()
    {
        AuthorAddress authorAddress=new AuthorAddress();
        authorAddress.setStreetNumber("21/508");
        authorAddress.setLocation("Kanpur");
        authorAddress.setState("UttarPradesh");
        Author author=new Author();
        author.setAuthorName("Ishika Thakur");
        author.setAuthorAddress(authorAddress);
        Subject subject1=new Subject();
        subject1.setSubject("Maths");
        Subject subject2=new Subject();
        subject2.setSubject("English");
        Subject subject3=new Subject();
        subject3.setSubject("hindi");
        subject1.setAuthor(author);
        subject2.setAuthor(author);
        subject3.setAuthor(author);
        author.addSubject(subject1);
        author.addSubject(subject2);
        author.addSubject(subject3);
        Book book=new Book();
        book.setBookName("Meluha");
        book.setAuthor(author);
        return author;
    }
}
