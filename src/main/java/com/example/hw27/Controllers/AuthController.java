package com.example.hw27.Controllers;


import com.example.hw27.APIs.ApiResponse;
import com.example.hw27.Models.User;
import com.example.hw27.Services.AuthServices;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServices authServices;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {
        authServices.register(user);
        return ResponseEntity.status(201).body(new ApiResponse("you register successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User user, @RequestBody @Valid User newUser) {
        authServices.update(user.getId(), newUser);
        return ResponseEntity.status(201).body(new ApiResponse("you updated successfully"));
    }


    @GetMapping("logout")
    public ResponseEntity logOut() {
        return ResponseEntity.status(200).body(new ApiResponse("you logged out successfully"));
    }

}
