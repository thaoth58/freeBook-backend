package com.freebook.API.controller;

import com.freebook.API.exception.ResourceNotFoundException;
import com.freebook.API.model.Book;
import com.freebook.API.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping(value = "/books")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Book save(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping(value = "/books")
    public Iterable<Book> all () {
        return bookRepository.findAll();
    }

    @GetMapping(value = "/books/{bookId}")
    public Book findByBookId (@PathVariable Integer bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book [bookId=" + bookId + "] can't be found"));
    }

    @DeleteMapping(value = "/books/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer bookId) {
        return bookRepository.findById(bookId).map(book -> {
            bookRepository.delete(book);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Book [bookId=" + bookId + "] can't be found"));
    }

    @PutMapping(value = "/books/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer bookId, @RequestBody Book newBook) {
        return bookRepository.findById(bookId).map(book -> {
            book.setName(newBook.getName());
            book.setAuthor(newBook.getAuthor());
            return ResponseEntity.ok(book);
        }).orElseThrow(() -> new ResourceNotFoundException("Book [bookId=" + bookId + "] can't be found"));
    }
}
