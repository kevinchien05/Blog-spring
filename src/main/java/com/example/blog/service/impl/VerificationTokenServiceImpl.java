package com.example.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.VerificationTokenService;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createVerificationToken(User user, String token) {
        user.setVerificationToken(token);
        userRepository.save(user);
    }

    @Override
    public String validateVerifactionToken(String token) {
        User user = userRepository.findByVerificationToken(token).orElse(null);

        if (user == null) {
            return "invalid";
        }

        user.setVerified(true);
        userRepository.save(user);
        return "valid";
    }

}
