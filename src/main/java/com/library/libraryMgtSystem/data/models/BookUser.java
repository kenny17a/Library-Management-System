package com.library.libraryMgtSystem.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookUser extends  User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
    private String phoneNumber;
    @ManyToMany
    private List<Book> borrowedBooks = new ArrayList<>();
    @ManyToMany
    private List<BookRequest> bookRequests = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
