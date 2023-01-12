package com.library.libraryMgtSystem.data.models;

import com.library.libraryMgtSystem.enums.BookRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private BookUser bookUser;
    @ManyToOne
    private Book book;
    private BookRequestStatus bookRequestStatus;
}
