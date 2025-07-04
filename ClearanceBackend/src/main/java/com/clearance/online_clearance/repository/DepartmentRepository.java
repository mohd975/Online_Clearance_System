package com.clearance.online_clearance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clearance.online_clearance.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
