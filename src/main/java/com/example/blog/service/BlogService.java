package com.example.blog.service;

import org.springframework.data.domain.Page;

import com.example.blog.dto.BlogDTO;
import com.example.blog.model.Blog;

public interface BlogService {
    public Page<Blog> getAllBlog(int page, int size);

    public Blog createBlog(BlogDTO dto);

    public Blog editBlog(Long id, BlogDTO dto);

    public String deleteBlog(Long id);
}
