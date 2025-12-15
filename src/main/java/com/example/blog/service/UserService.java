package com.example.blog.service;

import org.springframework.security.core.Authentication;

import com.example.blog.dto.UserCreateDTO;
import com.example.blog.dto.UserCreationResult;
import com.example.blog.model.User;

public interface UserService {

    public UserCreationResult registerUser(UserCreateDTO dto);
}
