package com.clearance.online_clearance.controller;

import org.springframework.web.bind.annotation.RestController;

import com.clearance.online_clearance.dto.DepartmentDTO;
import com.clearance.online_clearance.model.Department;
import com.clearance.online_clearance.repository.DepartmentRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/departments")
    public List<DepartmentDTO> getDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(dep -> new DepartmentDTO(dep.getId(), dep.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        return ResponseEntity.ok(department);
    }

    @PostMapping("/departments")
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {
        departmentRepository.save(department);
        return ResponseEntity.ok("Department created successfully");
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        department.setName(departmentDetails.getName());
        departmentRepository.save(department);

        return ResponseEntity.ok("Department updated successfully");

    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        departmentRepository.delete(department);
        return ResponseEntity.ok("Department deleted successfully");
    }

}
