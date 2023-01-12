package com.library.libraryMgtSystem.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CreateAdminResponse {
   private int statusCode;
    private String message;
}
