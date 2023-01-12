package com.library.libraryMgtSystem.controllers;

import com.library.libraryMgtSystem.data.models.Author;
import com.library.libraryMgtSystem.dtos.request.CreateAuthorRequest;
import com.library.libraryMgtSystem.dtos.response.CreateAuthorResponse;
import com.library.libraryMgtSystem.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<CreateAuthorResponse> createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest){
        return new ResponseEntity<>(authorService.createAuthor(createAuthorRequest), HttpStatus.CREATED);

    }
    @GetMapping("/findById/{authorId}")
    public ResponseEntity<Author> findAuthorById(@PathVariable("authorId") Long authorId){
        return new ResponseEntity<>(authorService.getAuthorById(authorId), HttpStatus.CREATED);
    }
}
