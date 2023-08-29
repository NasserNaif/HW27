package com.example.hw27.Controllers;


import com.example.hw27.APIs.ApiResponse;
import com.example.hw27.Models.User;
import com.example.hw27.Services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.status(200).body(adminService.getAllUsers());
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        adminService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted"));
    }
}
