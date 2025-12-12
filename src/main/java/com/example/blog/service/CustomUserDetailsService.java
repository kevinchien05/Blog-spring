package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blog.dto.CustomUserDetails;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!user.getVerified()) {
            // 🛑 Explicitly throw DisabledException so we can catch it later
            throw new DisabledException("Please verify your email to activate your account.");
        }

        return new CustomUserDetails(user);
    }
}
