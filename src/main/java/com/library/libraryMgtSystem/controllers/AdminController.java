package com.library.libraryMgtSystem.controllers;

import com.library.libraryMgtSystem.data.models.Admin;
import com.library.libraryMgtSystem.dtos.request.CreateAdminRequest;
import com.library.libraryMgtSystem.dtos.request.LoginRequest;
import com.library.libraryMgtSystem.dtos.request.UpdateAdminRequest;
import com.library.libraryMgtSystem.dtos.response.CreateAdminResponse;
import com.library.libraryMgtSystem.dtos.response.LoginResponse;
import com.library.libraryMgtSystem.dtos.response.UpdateAdminResponse;
import com.library.libraryMgtSystem.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<CreateAdminResponse> createAdmin(@RequestBody CreateAdminRequest createAdminRequest){
        return new ResponseEntity<>(adminService.createAdmin(createAdminRequest), HttpStatus.CREATED);
    }

    @GetMapping("/findById/{adminId}")
    public ResponseEntity<Admin> findAdminById(@PathVariable("adminId") Long adminId){
        return new ResponseEntity<>(adminService.findAdminById(adminId), HttpStatus.CREATED);
    }
    @GetMapping("/findByEmail/{adminEmail}")
    public ResponseEntity<Admin> findAdminByEmail(@PathVariable("adminEmail") String adminEmail){
        return new ResponseEntity<>(adminService.findAdminByEmail(adminEmail), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return adminService.adminLogin(loginRequest);
    }
    @GetMapping("/getAdmins")
    public ResponseEntity<List<Admin>> getAdmins(){
        return new ResponseEntity<>(adminService.getAdmins(), HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteById/{adminId}")
    public String deleteAdminById(@PathVariable("adminId") Long id){
        return adminService.deleteAdminById(id);
    }
    @DeleteMapping("/deleteByEmail/{adminEmail}")
    public String deleteAdminByEmail(@PathVariable("adminEmail") String email){
        return adminService.deleteAdminByEmail(email);
    }
    @PutMapping("/updateById")
    public UpdateAdminResponse updateAdminById(@RequestBody UpdateAdminRequest updateAdminRequest){
        return adminService.updateAdminById(updateAdminRequest);
    }
    @PutMapping("/updateByEmail")
    public UpdateAdminResponse updateAdminByEmail(@RequestBody UpdateAdminRequest updateAdminRequest){
        return adminService.updateAdminByEmail(updateAdminRequest);
    }
}
