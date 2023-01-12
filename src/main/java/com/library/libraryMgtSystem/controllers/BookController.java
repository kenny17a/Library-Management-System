package com.library.libraryMgtSystem.controllers;

import com.library.libraryMgtSystem.data.models.Author;
import com.library.libraryMgtSystem.data.models.Book;
import com.library.libraryMgtSystem.dtos.request.BookCreateRequest;
import com.library.libraryMgtSystem.dtos.request.CreateAuthorRequest;
import com.library.libraryMgtSystem.dtos.response.BookCreateResponse;
import com.library.libraryMgtSystem.dtos.response.CreateAuthorResponse;
import com.library.libraryMgtSystem.services.AuthorService;
import com.library.libraryMgtSystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;


    @PostMapping("/create")
    public ResponseEntity<BookCreateResponse> createBook(@RequestBody BookCreateRequest bookCreateRequest){
        return new ResponseEntity<>(bookService.createBook(bookCreateRequest), HttpStatus.CREATED);
    }

    @GetMapping("/findById/{bookId}")
    public ResponseEntity<Book> findBookById(@PathVariable("bookId") Long bookId){
        return new ResponseEntity<>(bookService.viewBookById(bookId), HttpStatus.CREATED);
    }


}
