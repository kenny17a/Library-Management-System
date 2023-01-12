package com.library.libraryMgtSystem.services;

import com.library.libraryMgtSystem.data.models.Admin;
import com.library.libraryMgtSystem.data.repositories.AdminRepository;
import com.library.libraryMgtSystem.dtos.request.CreateAdminRequest;
import com.library.libraryMgtSystem.dtos.request.LoginRequest;
import com.library.libraryMgtSystem.dtos.request.UpdateAdminRequest;
import com.library.libraryMgtSystem.dtos.response.CreateAdminResponse;
import com.library.libraryMgtSystem.dtos.response.LoginResponse;
import com.library.libraryMgtSystem.dtos.response.UpdateAdminResponse;
import com.library.libraryMgtSystem.exceptions.AdminException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest) {
     if (adminRepository.findAdminByEmail(createAdminRequest.getEmail().toLowerCase()).isPresent())
       throw new AdminException("Admin with " + createAdminRequest.getEmail() + " already exist");
        Admin admin = new Admin();
        admin.setFirstName(createAdminRequest.getFirstName());
        admin.setLastName(createAdminRequest.getLastName());
        admin.setEmail(createAdminRequest.getEmail().toLowerCase());
        admin.setPassword(createAdminRequest.getPassword());
        admin.setPhoneNumber(createAdminRequest.getPhoneNumber());
        adminRepository.save(admin);
        CreateAdminResponse createAdminResponse = new CreateAdminResponse();
        createAdminResponse.setMessage("Successful");
        createAdminResponse.setStatusCode(201);
        return createAdminResponse;

    }

    @Override
    public Admin findAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(()-> new AdminException("Admin with id "+id+ " does not exist" ));
    }

    @Override
    public Admin findAdminByEmail(String email) {
        return adminRepository.findAdminByEmail(email).
        orElseThrow(()->new AdminException("Admin with email "+email+ " does not exist"));
    }

    @Override
    public LoginResponse adminLogin(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        Admin foundAdmin = findAdminByEmail(loginRequest.getEmail());
        if (foundAdmin==null) throw new AdminException("Admin with email "+loginRequest.getEmail()+" doesnt exist");
        else if (!Objects.equals(loginRequest.getPassword(), foundAdmin.getPassword()))throw new AdminException("Password is incorrect");
        else loginResponse.setMessage("Login successful");
        return loginResponse;
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public String deleteAdminById(Long id) {
        Admin findAdmin = findAdminById(id);
        adminRepository.deleteById(findAdmin.getId());
        return "Admin deleted successfully";
    }

    @Override
    public String deleteAdminByEmail(String email) {
        Admin findAdmin = findAdminByEmail(email);
        adminRepository.deleteAdminByEmail(findAdmin.getEmail());
        return "Admin deleted mail successfully";
    }

    @Override
    public void deleteAll() {
        adminRepository.deleteAll();

    }

    @Override
    public UpdateAdminResponse updateAdminById(UpdateAdminRequest updateAdminRequest) {
        if (adminRepository.findById(updateAdminRequest.getId()).isEmpty()) throw new AdminException("Admin does not exist");
        Admin findAdmin = findAdminById(updateAdminRequest.getId());
        return getUpdateAdminResponse(updateAdminRequest, findAdmin);
    }

    @Override
    public UpdateAdminResponse updateAdminByEmail(UpdateAdminRequest updateAdminRequest) {
        if (adminRepository.findAdminByEmail(updateAdminRequest.getEmail()).isEmpty())throw new AdminException("Admin does not exist");
        Admin findAdmin = findAdminByEmail(updateAdminRequest.getEmail());
        return getUpdateAdminResponse(updateAdminRequest, findAdmin);
    }

    private UpdateAdminResponse getUpdateAdminResponse(UpdateAdminRequest updateAdminRequest, Admin findAdmin) {
        if (updateAdminRequest.getFirstName() != null)findAdmin.setFirstName(updateAdminRequest.getFirstName());
        if (updateAdminRequest.getLastName() != null)findAdmin.setLastName(updateAdminRequest.getLastName());
        if (updateAdminRequest.getPhoneNumber() != null)findAdmin.setPhoneNumber(updateAdminRequest.getPhoneNumber());
        adminRepository.save(findAdmin);
        UpdateAdminResponse updateAdminResponse = new UpdateAdminResponse();
        updateAdminResponse.setId(findAdmin.getId());
        return updateAdminResponse;
    }
}
