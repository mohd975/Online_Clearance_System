package com.clearance.online_clearance.controller;

import org.springframework.web.bind.annotation.RestController;

import com.clearance.online_clearance.model.Department;
import com.clearance.online_clearance.model.HOD;
import com.clearance.online_clearance.repository.DepartmentRepository;
import com.clearance.online_clearance.repository.HODRepository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class HODController {
    private final HODRepository hodRepository;
    private final DepartmentRepository departmentRepository;

    public HODController(HODRepository hodRepository, DepartmentRepository departmentRepository) {
        this.hodRepository = hodRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("hods")
    public List<HOD> getAllHods() {
        return hodRepository.findAll();
    }

    @GetMapping("/hods/{id}")
    public ResponseEntity<HOD> getHodById(@PathVariable Long id) {
        HOD hod = hodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HOD not found with id: " + id));
        return ResponseEntity.ok(hod);
    }

    @PostMapping("/hods")
    public ResponseEntity<String> createHod(@RequestBody HOD hod) {
        Long deptId = hod.getDepartment().getId();
        if (hod.getDepartment() == null || deptId == null) {
            throw new RuntimeException("Department ID must be provided");
        }

        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        hod.setDepartment(department);

        hodRepository.save(hod);
        return ResponseEntity.ok("HOD created successfully");
    }

    @PutMapping("/hods/{id}")
    public ResponseEntity<String> updateHod(@PathVariable Long id, @RequestBody HOD hodDetails) {
        HOD hod = hodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HOD not found with id: " + id));
        hod.setFullName(hodDetails.getFullName());
        hod.setEmail(hodDetails.getEmail());
        Department department = departmentRepository.findById(hodDetails.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        hod.setDepartment(department);
        hodRepository.save(hod);
        return ResponseEntity.ok("HOD updated successfully");
    }

    @DeleteMapping("/hods/{id}")
    public ResponseEntity<String> deleteHod(@PathVariable Long id) {
        HOD hod = hodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HOD not found with id: " + id));
        hodRepository.delete(hod);
        return ResponseEntity.ok("HOD deleted successfully");
    }

}
