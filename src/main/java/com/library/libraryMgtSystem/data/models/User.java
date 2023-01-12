package com.library.libraryMgtSystem.data.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @CreationTimestamp
    private Instant createdDate = Instant.now();
    @UpdateTimestamp
    private Instant modifiedDate = Instant.now();
}
