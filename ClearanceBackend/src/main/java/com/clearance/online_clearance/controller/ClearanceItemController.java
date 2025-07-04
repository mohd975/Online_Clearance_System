package com.clearance.online_clearance.controller;

import org.springframework.web.bind.annotation.RestController;

import com.clearance.online_clearance.model.ClearanceForm;
import com.clearance.online_clearance.model.ClearanceItem;
import com.clearance.online_clearance.repository.ClearanceFormRepository;
import com.clearance.online_clearance.repository.ClearanceItemRepository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ClearanceItemController {
    private final ClearanceItemRepository clearanceItemRepository;
    private final ClearanceFormRepository clearanceFormRepository;

    public ClearanceItemController(ClearanceItemRepository clearanceItemRepository,
            ClearanceFormRepository clearanceFormRepository) {
        this.clearanceItemRepository = clearanceItemRepository;
        this.clearanceFormRepository = clearanceFormRepository;
    }

    @GetMapping("/clearanceItems")
    public List<ClearanceItem> getAllClearanceItems() {
        return clearanceItemRepository.findAll();
    }

    @PostMapping("/clearanceItems")
    public ResponseEntity<String> createItem(@RequestBody ClearanceItem item) {
        if (item.getClearanceForm() == null || item.getClearanceForm().getId() == null) {
            throw new RuntimeException("Clearance Form ID must be provided");
        }
        ClearanceForm form = clearanceFormRepository.findById(item.getClearanceForm().getId())
                .orElseThrow(() -> new RuntimeException("Clearance Form not found"));
        item.setClearanceForm(form);
        clearanceItemRepository.save(item);
        return ResponseEntity.ok("Clearance Item created successfully");
    }

    @GetMapping("/clearanceItems/{id}")
    public ResponseEntity<ClearanceItem> getItemById(@PathVariable Long id) {
        ClearanceItem item = clearanceItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clearance Item not found with id: " + id));
        return ResponseEntity.ok(item);
    }

    @PutMapping("/clearanceItems/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody ClearanceItem itemDetails) {
        ClearanceItem item = clearanceItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clearance Item not found with id:" + id));
        item.setDescription(itemDetails.getDescription());
        item.setCleared(itemDetails.isCleared());
        if (itemDetails.getClearanceForm() != null && itemDetails.getClearanceForm().getId() != null) {
            ClearanceForm form = clearanceFormRepository.findById(itemDetails.getClearanceForm().getId())
                    .orElseThrow(() -> new RuntimeException("Clearance Form not found"));
            item.setClearanceForm(form);
        }
        clearanceItemRepository.save(item);
        return ResponseEntity.ok("Clearance Item updated successfully");

    }

    @DeleteMapping("/clearanceItems/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        ClearanceItem item = clearanceItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clearance Item not found with id: " + id));
        clearanceItemRepository.delete(item);
        return ResponseEntity.ok("Clearance Item deleted successfully");
    }
}
