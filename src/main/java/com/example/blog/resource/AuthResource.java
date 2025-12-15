package com.example.blog.resource;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.UserCreateDTO;
import com.example.blog.dto.UserCreationResult;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import com.example.blog.service.VerificationTokenService;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserCreateDTO dto) throws URISyntaxException {
        UserCreationResult result = userService.registerUser(dto);
        if (result.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result.getErrorMessage());
        }
    }

    @GetMapping("/verify-email")
    public ResponseEntity<Map<String, String>> getMethodName(@RequestParam String token) {
        String result = verificationTokenService.validateVerifactionToken(token);

        Map<String, String> response = new HashMap<>();
        if ("valid".equals(result)) {
            response.put("status", "success");
            response.put("message", "Your account has been verified successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid or expired verification token.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).body("Not authenticated");
        }

        String email = null;

        if (auth.getPrincipal() instanceof UserDetails userDetails) {
            email = userDetails.getUsername();
        }

        // Fallback
        if (email == null) {
            email = auth.getName();
        }

        User user = userRepository.findByEmail(email.toLowerCase());
        
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        return ResponseEntity.ok(user);
    }
}
