package com.library.libraryMgtSystem.data.repositories;

import com.library.libraryMgtSystem.data.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByIsbn(Long isbn);
    Optional<Book> findBookByBookName(String bookName);
    void deleteBookByIsbn(Long isbn);

}
