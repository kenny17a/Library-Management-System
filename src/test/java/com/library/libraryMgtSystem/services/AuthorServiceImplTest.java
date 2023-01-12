package com.library.libraryMgtSystem.services;


import com.library.libraryMgtSystem.data.models.Author;
import com.library.libraryMgtSystem.dtos.request.CreateAuthorRequest;

import com.library.libraryMgtSystem.dtos.response.CreateAdminResponse;
import com.library.libraryMgtSystem.dtos.response.CreateAuthorResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorServiceImplTest {
    @Autowired
    private AuthorService authorService;
    private CreateAuthorRequest createAuthorRequest;
    private CreateAuthorResponse createAuthorResponse;

    @BeforeEach
    void setUp() {
        createAuthorRequest = new CreateAuthorRequest();
        createAuthorRequest.setEmail("chinua@gmail.com");
        createAuthorRequest.setFirstName("Chinua");
        createAuthorRequest.setLastName("Achebe");

    }
    @AfterEach
    void tearDown() {
        authorService.deleteAll();
    }

    @Test
    void testThatAuthorCanBeCreated(){
        createAuthorResponse = authorService.createAuthor(createAuthorRequest);
        assertNotNull(createAuthorResponse.getMessage());
        System.out.println(createAuthorResponse.getMessage());
        assertEquals("Successful", createAuthorResponse.getMessage());
    }
    @Test
    void findAuthorByEmail(){
        createAuthorResponse = authorService.createAuthor(createAuthorRequest);
        Author findAuthor = authorService.getAuthorByEmail("chinua@gmail.com");
        assertEquals("Achebe", findAuthor.getLastName());
    }
    @Test
    void findAuthorById(){
        createAuthorResponse = authorService.createAuthor(createAuthorRequest);
        Author findAuthorById = authorService.getAuthorById(1L);
        assertEquals(1, findAuthorById.getId());
    }
    @Test
    void deleteAuthorByEmail(){
        createAuthorResponse = authorService.createAuthor(createAuthorRequest);
        List<Author> authorsBeforeDeletion = authorService.getAuthors();
        assertEquals(1, authorsBeforeDeletion.size());
        authorService.deleteAuthorByEmail("chinua@gmail.com");
        List<Author> authorsAfterDeletion = authorService.getAuthors();
        assertEquals(0, authorsAfterDeletion.size());
    }
}