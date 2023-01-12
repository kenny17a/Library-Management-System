package com.library.libraryMgtSystem.services;

import com.library.libraryMgtSystem.data.models.Book;
import com.library.libraryMgtSystem.dtos.request.BookAuthorRequest;
import com.library.libraryMgtSystem.dtos.request.BookCreateRequest;
import com.library.libraryMgtSystem.dtos.response.BookCreateResponse;

import java.util.List;

public interface BookService {
    BookCreateResponse createBook(BookCreateRequest bookCreateRequest);
    String deleteBookById(Long id);
    List<Book> viewBookByAuthor(BookAuthorRequest bookAuthorRequestRequest);
    String deleteAllBooks();
    Book viewBookById(Long id);
    Book viewBookByTitle(String name);
    List<Book> viewAllBooks();
    String deleteBookByIsbn(Long isbn);
    Book viewBookByIsbn(Long isbn);
}
