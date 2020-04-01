package com.example.demo.hibernate3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="BookManyToMany")
public class BookManyToMany
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String bookName;
    @ManyToMany(mappedBy = "bookManyToManySet")
    private Set<AuthorManyToMany> authorManytoManySet;

    public Set<AuthorManyToMany> getAuthorManytoManySet() {
        return authorManytoManySet;
    }

    public void setAuthorManytoManySet(Set<AuthorManyToMany> authorManytoManySet) {
        this.authorManytoManySet = authorManytoManySet;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookNameame() {
        return bookName;
    }

    public void setBookName(String name) {
        this.bookName = bookName;
    }


}
