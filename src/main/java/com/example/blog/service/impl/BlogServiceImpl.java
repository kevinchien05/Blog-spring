package com.example.blog.service.impl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.blog.dto.BlogDTO;
import com.example.blog.model.Blog;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Page<Blog> getAllBlog(int page, int size) {
        Page<Blog> listBlog = blogRepository.findAll(PageRequest.of(page, size));
        return listBlog; 
    }

    @Override
    public Blog createBlog(BlogDTO dto) {
        Blog result = new Blog();
        result.setName(dto.getName());
        result.setDescription(dto.getDescription());
        result.setPostDate(new Date(System.currentTimeMillis()));
        blogRepository.save(result);
        return result;
    }

    @Override
    public Blog editBlog(Long id, BlogDTO dto) {
        Blog result = blogRepository.findById(id).orElseThrow(null);
        result.setName(dto.getName());
        result.setDescription(dto.getDescription());
        blogRepository.save(result);
        return result;
    }

    @Override
    public String deleteBlog(Long id) {
        Blog result = blogRepository.findById(id).orElseThrow(null);
        blogRepository.delete(result);
        return "Success";
    }

    @Override
    public Blog getBlogDetail(Long id) {
        Blog result = blogRepository.findById(id).orElseThrow(null);
        return result;
    }

}
