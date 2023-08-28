package com.example.hw27.Controllers;


import com.example.hw27.APIs.ApiException;
import com.example.hw27.APIs.ApiResponse;
import com.example.hw27.Models.Blog;
import com.example.hw27.Models.User;
import com.example.hw27.Services.BlogServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogServices blogServices;


    @GetMapping("/get")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.status(200).body(blogServices.getAllBlogs());
    }


    @GetMapping("/get-my-blogs")
    public ResponseEntity<List<Blog>> getMyBlogs(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(blogServices.getMyBlogs(user.getId()));
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog) {
        blogServices.add(user.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog added"));
    }


    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateBlog(@AuthenticationPrincipal User user, Integer BlogID, @RequestBody @Valid Blog blog) {
        blogServices.update(user.getId(), BlogID, blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog updated"));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteBlog(@AuthenticationPrincipal User user, Integer BlogID) {
        blogServices.delete(user.getId(), BlogID);
        return ResponseEntity.status(200).body(new ApiResponse("blog deleted"));
    }


    @GetMapping("/search-by-id/{id}")
    public ResponseEntity<Blog> searchById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(blogServices.searchById(id));
    }

    @GetMapping("/search-by-title/{title}")
    public ResponseEntity<Blog> searchByTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(blogServices.searchByTitle(title));
    }
}
