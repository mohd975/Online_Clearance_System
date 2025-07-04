package com.clearance.online_clearance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clearance.online_clearance.dto.LoginRequest;
import com.clearance.online_clearance.dto.SignUpRequest;
import com.clearance.online_clearance.model.Users;
import com.clearance.online_clearance.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String signup(SignUpRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setApproved(false);
        userRepository.save(user);

        return "User registered successfully, pending approval by admin.";
    }

    public String login(LoginRequest request) {
        Users user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!user.isApproved()) {
            throw new RuntimeException("User not approved by admin yet");
        }

        return jwtService.generateToken(user.getUsername(), user.getRole());
    }

    public List<Users> getPendingUsers() {
        return userRepository.findByApprovedFalse();
    }
}
