package wn.batch.base_proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import wn.batch.base_proj.entity.BookEntity;
import wn.batch.base_proj.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/books")
@Slf4j
public class BooksController {
    
    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public List<BookEntity> getBooks() {
        log.info("Fetching all Records");
        return bookRepository.findAll();
    }
    
}
