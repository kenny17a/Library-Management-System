package com.library.libraryMgtSystem.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private Long bookQuantity;
    private Long isbn;
    private String yearPublished;
    @ManyToMany
    private List<Author> authors = new ArrayList<>();
    @CreationTimestamp
    private Instant createdDate = Instant.now();
    @UpdateTimestamp
    private Instant modifiedDate = Instant.now();
}
