package com.example.blog.service;

import com.example.blog.dto.UserCreateDTO;
import com.example.blog.dto.UserCreationResult;
import com.example.blog.model.User;

public interface UserService {

    public UserCreationResult registerUser(UserCreateDTO dto);

    public User checkEmail(String email);

    public void resetVerification(String email, String token);

    public void changePassword(String email, String password);
}
