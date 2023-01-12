package com.library.libraryMgtSystem.services;

import com.library.libraryMgtSystem.data.models.Admin;
import com.library.libraryMgtSystem.dtos.request.CreateAdminRequest;
import com.library.libraryMgtSystem.dtos.request.LoginRequest;
import com.library.libraryMgtSystem.dtos.request.UpdateAdminRequest;
import com.library.libraryMgtSystem.dtos.response.CreateAdminResponse;
import com.library.libraryMgtSystem.dtos.response.LoginResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;
    private CreateAdminRequest createAdminRequest;
    private CreateAdminResponse createAdminResponse;
    private Admin admin;


    @BeforeEach
    void setUp() {
        createAdminRequest = new CreateAdminRequest();
        createAdminRequest.setFirstName("kehinde");
        createAdminRequest.setLastName("Ibrahim");
        createAdminRequest.setEmail("kenny@gmail.com");
        createAdminRequest.setPassword("kenny123");
        createAdminRequest.setPhoneNumber("08117403669");
        createAdminResponse = adminService.createAdmin(createAdminRequest);

    }
    @AfterEach
    void afterEach(){
        adminService.deleteAll();

    }
    @Test
    void testThatAdminCan_Be_Created(){
        assertNotNull(createAdminResponse.getMessage());
//        assertEquals("kenny@gmail.com", createAdminResponse.getEmail());
    }
    @Test
    void testThatAdmin_Can_Be_Found_By_Email(){
        Admin findAdmin = adminService.findAdminByEmail("kenny@gmail.com");
//        assertEquals(createAdminResponse.getEmail(), findAdmin.getEmail());
    }

    @Test
    void testThatIdCannotBeNull(){
        Admin findAdmin = adminService.findAdminById(1L);
        System.out.println(findAdmin);
        assertNotNull(findAdmin);
    }
    @Test
    void testThatAdminCanLoginUsingTheCorrectEmailAndPassWord(){
        CreateAdminRequest createAdminRequest1 = new CreateAdminRequest();
        createAdminRequest1.setFirstName("murphy");
        createAdminRequest1.setLastName("raymond");
        createAdminRequest1.setEmail("murphy@gmail.com");
        createAdminRequest1.setPassword("morphy01");
        createAdminRequest1.setPhoneNumber("09160393982");
        var saved = adminService.createAdmin(createAdminRequest1);
        assertEquals(201,saved.getStatusCode());

//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setPassword("morphy01");
//        loginRequest.setEmail("murphy@gmail.com");
//        LoginResponse loginResponse = adminService.adminLogin(loginRequest);
//        assertEquals("Login successful", loginResponse.getMessage());
    }
    @Test
    void testThatListOfAdminIsGreaterIncreasesByOneWhenANewAdminIsCreatedAndSaved(){
        List<Admin> adminsAfterSavingFirstAdmin = adminService.getAdmins();
        assertEquals(1, adminsAfterSavingFirstAdmin.size());
        CreateAdminRequest createAdminRequest1 = new CreateAdminRequest();
        createAdminRequest1.setEmail("tonyblar@gmail.com");
        createAdminRequest1.setFirstName("tony");
        createAdminRequest1.setLastName("blar");
        createAdminRequest1.setPassword("blar90");
        createAdminRequest1.setPhoneNumber("09029676625");

        adminService.createAdmin(createAdminRequest1);

        List<Admin> adminsAfterSavingSecondAdmin = adminService.getAdmins();
        assertEquals(2, adminsAfterSavingSecondAdmin.size());
    }
    @Test
    void testThatSizeOfAdminListDecreaseByOneWhenOneAdminIsDeleted(){
        List<Admin> listOfAdminsAfterSavingOneAdmin = adminService.getAdmins();
        assertEquals(1, listOfAdminsAfterSavingOneAdmin.size());

        adminService.deleteAdminByEmail("kenny@gmail.com");

        List<Admin> listOfAdminsAfterDeletingOneAdmin = adminService.getAdmins();
        assertEquals(0, listOfAdminsAfterDeletingOneAdmin.size());
    }
    @Test
    void adminCanBeUpdated(){
        System.out.println(createAdminResponse.getMessage());
        UpdateAdminRequest updateAdminRequest = new UpdateAdminRequest();
        updateAdminRequest.setId(1L);
        updateAdminRequest.setFirstName("john");
        updateAdminRequest.setLastName("tobi");
        updateAdminRequest.setEmail("johntobi@gmail.com");
        adminService.updateAdminById(updateAdminRequest);
        Admin foundUserAfterUpdate = adminService.findAdminById(1L);
        assertEquals(foundUserAfterUpdate.getFirstName(), "john" );
        assertNotNull(foundUserAfterUpdate.getFirstName());
    }

}