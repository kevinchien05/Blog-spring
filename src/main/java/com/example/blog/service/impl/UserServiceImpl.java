package com.example.blog.service.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.dto.UserCreateDTO;
import com.example.blog.dto.UserCreationResult;
import com.example.blog.event.OnRegistrationCompleteEvent;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.EmailService;
import com.example.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Value("${frontend-url}")
    private String frontendUrl;

    @Override
    public UserCreationResult registerUser(UserCreateDTO dto) {
        if (userRepository.findByEmail(dto.getEmail().toLowerCase()) != null) {
            return UserCreationResult.error("Email already used");
        } else if (userRepository.findByUsername(dto.getUsername().toLowerCase()) != null) {
            return UserCreationResult.error("Username already used");
        }
        User user = new User();
        user.setUsername(dto.getUsername().toLowerCase());
        user.setEmail(dto.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setVerified(false);
        userRepository.save(user);

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));

        return UserCreationResult.success(user);
    }

    @Override
    public User checkEmail(String email) {
        User user = userRepository.findByEmail(email.toLowerCase());
        return user;
    }

    @Override
    public void resetVerification(String email, String token) {
        String encodedTxt = URLEncoder.encode(token, StandardCharsets.UTF_8);
        String confirmationUrl = frontendUrl + "/change/password?email=" + encodedTxt;
        emailService.sendEmail(email, "Email Verification", "Click the link to reset your password: " + confirmationUrl);
    }

    @Override
    public void changePassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

}
