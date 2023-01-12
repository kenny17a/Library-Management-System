package com.library.libraryMgtSystem.services;

import com.library.libraryMgtSystem.data.models.Author;
import com.library.libraryMgtSystem.dtos.request.CreateAuthorRequest;
import com.library.libraryMgtSystem.dtos.response.CreateAuthorResponse;

import java.util.List;

public interface AuthorService {
    CreateAuthorResponse createAuthor(CreateAuthorRequest createAuthorRequest);
    Author getAuthorById(Long id);
    Author getAuthorByEmail(String email);

    void deleteAll();

    List<Author> getAuthors();
    String deleteAuthorByEmail(String email);
}
