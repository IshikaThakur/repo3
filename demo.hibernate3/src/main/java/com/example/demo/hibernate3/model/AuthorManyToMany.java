package com.example.demo.hibernate3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="AuthorManyToMany")
public class AuthorManyToMany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String authorName;
    @ManyToMany(cascade =CascadeType.ALL)
    @JoinTable(name = "author_books",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<BookManyToMany> bookManyToManySet;
    public Set<BookManyToMany> getBookManyToManySet() {
        return bookManyToManySet;
    }

    public void setBookManyToManySet(Set<BookManyToMany> bookManyToManySet) {
        this.bookManyToManySet = bookManyToManySet;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String name) {
        this.authorName = authorName;
    }


}
