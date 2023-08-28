package com.example.hw27.Services;


import com.example.hw27.Models.User;
import com.example.hw27.Repos.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServices {

    private final AuthRepo authRepo;

    public void register(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("USER");

        authRepo.save(user);
    }

    public void update(Integer id, User newUser) {
        User user = authRepo.findUserById(id);

        String hash = new BCryptPasswordEncoder().encode(newUser.getPassword());

        user.setEmail(newUser.getEmail());
        user.setPassword(hash);
        user.setUsername(newUser.getUsername());

        authRepo.save(user);
    }


}
