package com.clearance.online_clearance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clearance.online_clearance.model.HOD;

public interface HODRepository extends JpaRepository<HOD, Long> {
    boolean existsByEmail(String email);
}
