package com.clearance.online_clearance.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.clearance.online_clearance.model.Approval;
import com.clearance.online_clearance.model.ClearanceForm;
import com.clearance.online_clearance.model.HOD;
import com.clearance.online_clearance.repository.ApprovalRepository;
import com.clearance.online_clearance.repository.ClearanceFormRepository;
import com.clearance.online_clearance.repository.HODRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ApprovalController {
    private final ApprovalRepository approvalRepository;
    private final ClearanceFormRepository clearanceFormRepository;
    private final HODRepository hodRepository;

    public ApprovalController(ApprovalRepository approvalRepository, ClearanceFormRepository clearanceFormRepository,
            HODRepository hodRepository) {
        this.approvalRepository = approvalRepository;
        this.clearanceFormRepository = clearanceFormRepository;
        this.hodRepository = hodRepository;
    }

    @GetMapping("/approvals")
    public List<Approval> getAllApprovals() {
        return approvalRepository.findAll();
    }

    @GetMapping("/approvals/{id}")
    public ResponseEntity<Approval> getApprovalById(@PathVariable Long id) {
        Approval approval = approvalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval not found with id: " + id));
        return ResponseEntity.ok(approval);
    }

    @PostMapping("/approvals")
    public ResponseEntity<String> createApproval(@RequestBody Approval approval) {
        if (approval.getClearanceForm() == null || approval.getClearanceForm().getId() == null
                || approval.getHod() == null || approval.getHod().getId() == null) {
            throw new RuntimeException("Clearance Form ID and HOD ID must be provided");
        }
        ClearanceForm form = clearanceFormRepository.findById(approval.getClearanceForm().getId())
                .orElseThrow(() -> new RuntimeException("Clearance Form not found"));
        HOD hod = hodRepository.findById(approval.getHod().getId())
                .orElseThrow(() -> new RuntimeException("HOD not found"));
        approval.setClearanceForm(form);
        approval.setHod(hod);
        approval.setTimeStamp(LocalDateTime.now());
        approvalRepository.save(approval);
        return ResponseEntity.ok("Approval created successfully");
    }

    @PutMapping("/approvals/{id}")
    public ResponseEntity<String> updateApproval(@PathVariable Long id, @RequestBody Approval approvalDetails) {
        Approval approval = approvalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval not found with id: " + id));
        if (!List.of("APPROVED", "REJECTED", "PENDING").contains(approvalDetails.getStatus().toUpperCase())) {
            throw new RuntimeException("Invalid status");
        }
        approval.setStatus(approvalDetails.getStatus());
        approval.setComment(approvalDetails.getComment());
        approval.setTimeStamp(LocalDateTime.now());
        if (approvalDetails.getClearanceForm() != null && approvalDetails.getClearanceForm().getId() != null) {
            ClearanceForm form = clearanceFormRepository.findById(approvalDetails.getClearanceForm().getId())
                    .orElseThrow(() -> new RuntimeException("Clearance Form not found"));
            approval.setClearanceForm(form);
        }
        if (approvalDetails.getHod() != null && approvalDetails.getHod().getId() != null) {
            HOD hod = hodRepository.findById(approvalDetails.getHod().getId())
                    .orElseThrow(() -> new RuntimeException("HOD not found"));
            approval.setHod(hod);
        }
        approvalRepository.save(approval);
        return ResponseEntity.ok("Approval updated successfully");
    }

    @DeleteMapping("/approvals/{id}")
    public ResponseEntity<String> deleteApproval(@PathVariable Long id) {
        Approval approval = approvalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval not found with id: " + id));
        approvalRepository.delete(approval);
        return ResponseEntity.ok("Approval deleted successfully");
    }

}
