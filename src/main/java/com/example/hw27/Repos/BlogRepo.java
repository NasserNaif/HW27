package com.example.hw27.Repos;


import com.example.hw27.Models.Blog;
import com.example.hw27.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer> {
    List<Blog> findAllByUser(User user);

    Blog findBlogByTitle(String title);


    Blog findBlogById(Integer id);
}
