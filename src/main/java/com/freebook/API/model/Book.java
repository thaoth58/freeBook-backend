package com.freebook.API.model;

import javax.persistence.*;

import java.io.Serializable;

@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="ID", updatable = false)
    private Integer id;

    @Column(name="NAME")
    private String name;

    @Column(name="AUTHOR")
    private String author;

    public Book() {
        super();
    }

    public Book(String name, String author) {
        super();
        this.name = name;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
