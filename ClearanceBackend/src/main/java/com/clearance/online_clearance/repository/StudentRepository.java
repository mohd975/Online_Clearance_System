package com.clearance.online_clearance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clearance.online_clearance.model.Student;
import com.clearance.online_clearance.model.Users;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByRegNumber(String regNumber);

    Optional<Student> findByUser(Users user);

}
