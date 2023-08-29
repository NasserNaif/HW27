package com.example.hw27.Services;

import com.example.hw27.APIs.ApiException;
import com.example.hw27.Models.User;
import com.example.hw27.Repos.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AuthRepo authRepo;

    public List<User> getAllUsers() {
        return authRepo.findAll();
    }

    public void deleteUser(Integer id) {
        User user = authRepo.findUserById(id);

        if (user == null) {
            throw new ApiException("user doesn't exist");
        }

        authRepo.delete(user);
    }


}
