package com.library.libraryMgtSystem.services;

import com.library.libraryMgtSystem.dtos.request.BookCreateRequest;
import com.library.libraryMgtSystem.dtos.request.CreateAuthorRequest;
import com.library.libraryMgtSystem.dtos.response.BookCreateResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookServiceImplTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    private BookCreateResponse bookCreateResponse;
    private CreateAuthorRequest createAuthorRequest;
    private BookCreateRequest bookCreateRequest;


    @BeforeEach
    void setUp() {
        createAuthorRequest = new CreateAuthorRequest();
        createAuthorRequest.setEmail("jk@gmail.com");
        createAuthorRequest.setFirstName("ab");
        authorService.createAuthor(createAuthorRequest);
        bookCreateRequest = new BookCreateRequest();
        bookCreateRequest.setAuthorId(1L);
        bookCreateRequest.setIsbn(1515L);
        bookCreateRequest.setBookName("Lion and the jewel");
        bookCreateRequest.setYearPublished("1959");
    }

    @AfterEach
    void tearDown() {
        bookService.deleteAllBooks();
        authorService.deleteAll();
    }
    @Test
    void createNewBook(){
            BookCreateResponse bookCreateResponse = bookService.createBook(bookCreateRequest);
            assertNotNull(bookCreateResponse.getId());
    }


}