package com.library.libraryMgtSystem.dtos.request;

import lombok.Data;

@Data
public class CreateAdminRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
