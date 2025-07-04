package com.clearance.online_clearance.controller;

import org.springframework.web.bind.annotation.RestController;

import com.clearance.online_clearance.dto.ClearanceFormDTO;
import com.clearance.online_clearance.dto.ClearanceRequestDTO;
import com.clearance.online_clearance.model.ClearanceForm;
import com.clearance.online_clearance.model.Department;
import com.clearance.online_clearance.model.Student;
import com.clearance.online_clearance.model.Users;
import com.clearance.online_clearance.repository.ClearanceFormRepository;
import com.clearance.online_clearance.repository.DepartmentRepository;
import com.clearance.online_clearance.repository.StudentRepository;
import com.clearance.online_clearance.repository.UserRepository;


import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ClearanceFormController {
    private final ClearanceFormRepository clearanceFormRepository;
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public ClearanceFormController(ClearanceFormRepository clearanceFormRepository,
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository) {
        this.clearanceFormRepository = clearanceFormRepository;
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/clearanceForms")
    public List<ClearanceForm> getAllClearanceForms() {
        return clearanceFormRepository.findAll();
    }

    @GetMapping("/clearanceForms/{id}")

    public ResponseEntity<ClearanceForm> getClearanceFormById(@PathVariable Long id) {
        ClearanceForm form = clearanceFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clearance Form not found with id: " + id));
        return ResponseEntity.ok(form);
    }

    @PostMapping("/clearanceForms")
    public ResponseEntity<String> createForm(@RequestBody ClearanceForm clearanceForm, Authentication authentication) {
        String username = authentication.name();

        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        if (clearanceForm.getDepartment() == null || clearanceForm.getDepartment().getId() == null) {
            throw new RuntimeException("Department ID must be provided");
        }

        Department department = departmentRepository.findById(clearanceForm.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        clearanceForm.setStudent(student);
        clearanceForm.setDepartment(department);
        clearanceFormRepository.save(clearanceForm);
        return ResponseEntity.ok("Clearance Form created successfully");
    }

    @PutMapping("/clearanceForms/{id}")
    public ResponseEntity<String> updateForm(@PathVariable Long id, @RequestBody ClearanceForm formDetails) {
        ClearanceForm form = clearanceFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clearance Form not found with id: " + id));
        form.setSemester(formDetails.getSemester());
        form.setYear(formDetails.getYear());
        form.setStatus(formDetails.getStatus());

        if (formDetails.getStudent() != null && formDetails.getStudent().getId() != null) {
            Student student = studentRepository.findById(formDetails.getStudent().getId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            form.setStudent(student);
        }

        if (formDetails.getDepartment() != null && formDetails.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(formDetails.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            form.setDepartment(department);
        }
        clearanceFormRepository.save(form);
        return ResponseEntity.ok("Clearance Form updated successfully");
    }

    @GetMapping("/my-clearance-forms")
    public ResponseEntity<List<ClearanceFormDTO>> testAllForms() {
        List<ClearanceForm> forms = clearanceFormRepository.findAll();

        List<ClearanceFormDTO> responseForms = forms.stream()
                .map(ClearanceFormDTO::new)
                .toList();

        return ResponseEntity.ok(responseForms);
    }

    @DeleteMapping("/clearanceForms/{id}")
    public ResponseEntity<String> deleteForm(@PathVariable Long id) {
        ClearanceForm form = clearanceFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clearance Form not found with id: " + id));
        clearanceFormRepository.delete(form);
        return ResponseEntity.ok("Clearance Form deleted successfully");
    }

    @PutMapping("/admin/clearanceForms/{id}/approve")
    public ResponseEntity<String> approveClearance(@PathVariable Long id) {
        ClearanceForm form = clearanceFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clearance form not found"));
        form.setStatus("PENDING");
        clearanceFormRepository.save(form);
        return ResponseEntity.ok("Clearance form approved successfully");
    }

    @PutMapping("/admin/clearanceForms/{id}/reject")
    public ResponseEntity<String> rejectClearance(@PathVariable Long id) {
        ClearanceForm form = clearanceFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clearance form not found"));
        form.setStatus("REJECTED");
        clearanceFormRepository.save(form);
        return ResponseEntity.ok("Clearance form rejected successfully");
    }

    @PostMapping("/clearance-requests")
    public ResponseEntity<String> requestClearance(@RequestBody ClearanceRequestDTO requestDTO,
            Authentication authentication) {
        String username = authentication.name();
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        ClearanceForm form = new ClearanceForm();
        form.setSemester(requestDTO.getSemester());
        form.setYear(requestDTO.getYear());
        form.setStatus("Pending");
        form.setStudent(student);

        clearanceFormRepository.save(form);

        return ResponseEntity.ok("Clearance request submitted successfully.");
    }

    @GetMapping("/clearance-requests-summary")
    public ResponseEntity<List<ClearanceRequestDTO>> getClearanceRequestSummary() {
        List<ClearanceRequestDTO> summaries = clearanceFormRepository.findAllRequestSummaries();
        return ResponseEntity.ok(summaries);
    }
}
