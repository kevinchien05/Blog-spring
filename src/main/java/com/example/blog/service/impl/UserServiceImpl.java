package com.example.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.dto.UserCreateDTO;
import com.example.blog.dto.UserCreationResult;
import com.example.blog.event.OnRegistrationCompleteEvent;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

}
