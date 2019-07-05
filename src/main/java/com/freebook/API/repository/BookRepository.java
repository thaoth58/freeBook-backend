package com.freebook.API.repository;

import com.freebook.API.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
