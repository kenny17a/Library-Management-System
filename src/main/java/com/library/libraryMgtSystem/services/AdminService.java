package com.library.libraryMgtSystem.services;

import com.library.libraryMgtSystem.data.models.Admin;
import com.library.libraryMgtSystem.dtos.request.CreateAdminRequest;
import com.library.libraryMgtSystem.dtos.request.LoginRequest;
import com.library.libraryMgtSystem.dtos.request.UpdateAdminRequest;
import com.library.libraryMgtSystem.dtos.response.CreateAdminResponse;
import com.library.libraryMgtSystem.dtos.response.LoginResponse;
import com.library.libraryMgtSystem.dtos.response.UpdateAdminResponse;

import java.util.List;

public interface AdminService {
    CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest);
    Admin findAdminById(Long id);
    Admin findAdminByEmail(String email);
    LoginResponse adminLogin(LoginRequest loginRequest);
    List<Admin> getAdmins();
    String deleteAdminById(Long id);
    String deleteAdminByEmail(String email);
    void deleteAll();
    UpdateAdminResponse updateAdminById(UpdateAdminRequest updateAdminRequest);
    UpdateAdminResponse updateAdminByEmail(UpdateAdminRequest updateAdminRequest);
}
