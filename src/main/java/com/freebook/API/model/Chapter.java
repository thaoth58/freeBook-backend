package com.freebook.API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="chapter")
@Entity
public class Chapter implements Serializable {
    @Id
    @Column(name="ID", updatable = false)
    private Integer id;

    @Column(name="TITLE")
    private String title;

    @Column(name="CONTENT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOOKID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Book book;

    public Chapter() {
        super();
    }

    public Chapter(String title, String content, Book book) {
        super();
        this.title = title;
        this.content = content;
        this.book = book;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
