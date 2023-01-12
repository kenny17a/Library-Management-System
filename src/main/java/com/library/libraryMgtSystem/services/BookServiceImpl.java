package com.library.libraryMgtSystem.services;

import com.library.libraryMgtSystem.data.models.Author;
import com.library.libraryMgtSystem.data.models.Book;
import com.library.libraryMgtSystem.data.repositories.BookRepository;
import com.library.libraryMgtSystem.dtos.request.BookAuthorRequest;
import com.library.libraryMgtSystem.dtos.request.BookCreateRequest;
import com.library.libraryMgtSystem.dtos.response.BookCreateResponse;
import com.library.libraryMgtSystem.exceptions.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;

    @Override
    public BookCreateResponse createBook(BookCreateRequest bookCreateRequest) {
        if (bookRepository.findBookByIsbn(bookCreateRequest.getIsbn()).isPresent())
            throw new BookException("Book with " + bookCreateRequest.getIsbn() + " already exist");
        Author author = authorService.getAuthorById(bookCreateRequest.getAuthorId());
        Book book = new Book();
        book.setIsbn(bookCreateRequest.getIsbn());
        book.getAuthors().add(author);
        book.setYearPublished(bookCreateRequest.getYearPublished());
        book.setBookName(bookCreateRequest.getBookName());
        book.setBookQuantity(bookCreateRequest.getIsbn());
        Book savedBook = bookRepository.save(book);

        BookCreateResponse bookCreateResponse = new BookCreateResponse();
        bookCreateResponse.setMessage("Successful");
        bookCreateResponse.setStatusCode(201);
        bookCreateResponse.setId(savedBook.getId());

        return bookCreateResponse;
    }

    @Override
    public String deleteBookById(Long id) {
        bookRepository.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public List<Book> viewBookByAuthor(BookAuthorRequest bookAuthorRequest) {
        return null;
    }

    @Override
    public String deleteAllBooks() {
        bookRepository.deleteAll();
        return "All books deleted successfully";
    }

    @Override
    public Book viewBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()->
                new BookException("Book with" + id + "does not exist"));
    }

    @Override
    public Book viewBookByTitle(String name) {
        return bookRepository.findBookByBookName(name).orElseThrow(()
                -> new BookException("Book with "+ name + " does not exist"));
    }

    @Override
    public List<Book> viewAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public String deleteBookByIsbn(Long isbn) {
        bookRepository.deleteBookByIsbn(isbn);
        return "Book deleted successfully";

    }

    @Override
    public Book viewBookByIsbn(Long isbn) {
        return bookRepository.findBookByIsbn(isbn).orElseThrow(() ->
                new BookException("Book with " + isbn + " does not exist"));
    }
}
