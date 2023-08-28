package com.example.hw27.Services;


import com.example.hw27.APIs.ApiException;
import com.example.hw27.Models.Blog;
import com.example.hw27.Models.User;
import com.example.hw27.Repos.AuthRepo;
import com.example.hw27.Repos.BlogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogServices {

    private final BlogRepo blogRepo;
    private final AuthRepo authRepo;

    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    public List<Blog> getMyBlogs(Integer id) {
        User user = authRepo.findUserById(id);

        return blogRepo.findAllByUser(user);
    }


    public void add(Integer id, Blog blog) {
        User user = authRepo.findUserById(id);
        blog.setUser(user);
        blogRepo.save(blog);
    }

    public void update(Integer userID, Integer blogID, Blog newBlog) {
        Blog blog = blogRepo.findBlogById(blogID);

        if (!Objects.equals(blog.getUser().getId(), userID)) {
            throw new ApiException("sorry! you can't update this blog because you are not blog's owner");
        }
        blog.setBody(newBlog.getBody());
        blog.setTitle(newBlog.getTitle());

        blogRepo.save(blog);
    }

    public void delete(Integer userID, Integer blogID) {
        Blog blog = blogRepo.findBlogById(blogID);

        if (!Objects.equals(blog.getUser().getId(), userID)) {
            throw new ApiException("sorry! you can't delete this blog because you are not blog's owner");
        }

        blogRepo.delete(blog);
    }


    public Blog searchById(Integer id) {
        Blog blog = blogRepo.findBlogById(id);

        if (blog == null) {
            throw new ApiException("blog doesn't exist");
        }

        return blog;
    }

    public Blog searchByTitle(String title) {
        Blog blog = blogRepo.findBlogByTitle(title);

        if (blog == null) {
            throw new ApiException("blog doesn't exist");
        }

        return blog;
    }


}
