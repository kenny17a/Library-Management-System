package com.library.libraryMgtSystem.dtos.response;

import lombok.Data;

@Data
public class BookCreateResponse {
    private Long id;
    private int statusCode;
    private String message;
}
