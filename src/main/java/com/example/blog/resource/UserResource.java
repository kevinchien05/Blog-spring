package com.example.blog.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.service.UserService;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    

}
