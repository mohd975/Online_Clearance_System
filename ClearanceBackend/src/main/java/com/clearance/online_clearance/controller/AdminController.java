package com.clearance.online_clearance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clearance.online_clearance.model.Users;

import com.clearance.online_clearance.repository.UserRepository;
import com.clearance.online_clearance.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Transactional
    @PutMapping("/approve/{userId}")
    public ResponseEntity<Map<String, String>> approveUser(@PathVariable Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        user.setApproved(true);
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User approved successfully");
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PutMapping("/reject/{userId}")
    public ResponseEntity<Map<String, String>> rejectUser(@PathVariable Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        userRepository.delete(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User rejected successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pending-users")
    public List<Users> getPendingUsers() {
        return userService.getPendingUsers();
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PutMapping("/assign-role/{id}")
    public ResponseEntity<String> assignRole(@PathVariable Long id, @RequestBody RoleRequest roleRequest) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(roleRequest.getRole());
        userRepository.save(user);
        return ResponseEntity.ok("Role assigned successfully");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");

        return ResponseEntity.ok(response);
    }

    static class RoleRequest {
        private String role;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

}
