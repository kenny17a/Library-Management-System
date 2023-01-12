package com.library.libraryMgtSystem.data.repositories;

import com.library.libraryMgtSystem.data.models.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {
}
