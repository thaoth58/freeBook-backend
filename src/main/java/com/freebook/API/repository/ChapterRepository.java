package com.freebook.API.repository;

import com.freebook.API.model.Chapter;
import org.springframework.data.repository.CrudRepository;

public interface ChapterRepository extends CrudRepository<Chapter, Integer> {
    Iterable<Chapter> findByBookId(Integer bookId);
}
