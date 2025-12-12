package com.example.blog.service;

import com.example.blog.model.User;

public interface VerificationTokenService {

    public void createVerificationToken(User user, String token);

    public String validateVerifactionToken(String token);
}
