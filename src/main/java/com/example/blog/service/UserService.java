package com.example.blog.service;

import com.example.blog.dto.UserCreateDTO;
import com.example.blog.dto.UserCreationResult;

public interface UserService {

    public UserCreationResult registerUser(UserCreateDTO dto);
}
