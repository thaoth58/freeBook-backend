package com.freebook.API.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String author;

    public Book() {
        super();
    }

    public Book(String name, String author) {
        super();
        this.name = name;
        this.author = author;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }
}
