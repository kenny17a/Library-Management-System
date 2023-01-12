package com.library.libraryMgtSystem.dtos.request;

import lombok.Data;

@Data
public class UpdateAdminRequest {
    private Long id;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
}
