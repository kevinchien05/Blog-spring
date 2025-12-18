package com.example.blog.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.BlogDTO;
import com.example.blog.model.Blog;
import com.example.blog.service.BlogService;



@RestController
@RequestMapping("/blog")
public class BlogResource {

    @Autowired
    private BlogService blogService;

    @GetMapping("/list")
    public ResponseEntity<Page<Blog>> getAllBlog(@RequestParam int page, @RequestParam int size) {
        Page<Blog> result = blogService.getAllBlog(page, size);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<Blog> createBlog(@RequestBody BlogDTO dto) {
        Blog result = blogService.createBlog(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Blog> editBlog(@PathVariable Long id, @RequestBody BlogDTO dto) {
        Blog result = blogService.editBlog(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id){
        String result = blogService.deleteBlog(id);
        return ResponseEntity.ok().build();
    }
    
}
