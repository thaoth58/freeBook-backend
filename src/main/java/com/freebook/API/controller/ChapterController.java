package com.freebook.API.controller;

import com.freebook.API.exception.ResourceNotFoundException;
import com.freebook.API.model.Chapter;
import com.freebook.API.repository.BookRepository;
import com.freebook.API.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChapterController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @PostMapping(value = "/books/{bookId}/chapters")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Chapter save(@PathVariable Integer bookId, @RequestBody Chapter chapter) {
        return bookRepository.findById(bookId).map(book -> {
            chapter.setBook(book);
            return chapterRepository.save(chapter);
        }).orElseThrow(() -> new ResourceNotFoundException("Book [bookId=" + bookId + "] can't be found"));
    }

    @GetMapping(value = "/books/{bookId}/chapters")
    public Iterable<Chapter> all (@PathVariable Integer bookId) {
        return chapterRepository.findByBookId(bookId);
    }

    @DeleteMapping(value = "/books/{bookId}/chapters/{chapterId}")
    public ResponseEntity<?> deleteChapter(@PathVariable Integer bookId, @PathVariable Integer chapterId) {
        if (!bookRepository.existsById(bookId)) {
            throw new ResourceNotFoundException("Book [bookId=" + bookId + "] can't be found");
        }

        return chapterRepository.findById(chapterId).map(chapter -> {
            chapterRepository.delete(chapter);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Chapter [chapterId=" + chapterId + "] can't be found"));
    }

    @PutMapping(value = "/books/{bookId}/chapters/{chapterId")
    public ResponseEntity<Chapter> updateChapter(@PathVariable Integer bookId, @PathVariable Integer chapterId, @RequestBody Chapter newChapter) {
        if (!bookRepository.existsById(bookId)) {
            throw new ResourceNotFoundException("Book [bookId=" + bookId + "] can't be found");
        }

        return chapterRepository.findById(chapterId).map(chapter -> {
            chapter.setContent(newChapter.getContent());
            chapter.setTitle(newChapter.getTitle());
            return ResponseEntity.ok(chapter);
        }).orElseThrow(() -> new ResourceNotFoundException("Chapter [chapterId=" + chapterId + "] can't be found"));
    }
}
