package com.library.libraryMgtSystem.dtos.request;

import lombok.Data;

@Data
public class BookCreateRequest {
    private Long isbn;
    private String yearPublished;
    private String bookName;
    private Long authorId;
}
