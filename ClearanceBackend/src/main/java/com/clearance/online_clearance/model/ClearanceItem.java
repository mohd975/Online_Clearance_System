package com.clearance.online_clearance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class ClearanceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "isCleared", nullable = false)
    private boolean isCleared;

    @ManyToOne
    @JoinColumn(name = "clearanceForm_id" , nullable = false)
    private ClearanceForm clearanceForm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCleared() {
        return isCleared;
    }

    public void setCleared(boolean isCleared) {
        this.isCleared = isCleared;
    }

    public ClearanceForm getClearanceForm() {
        return clearanceForm;
    }

    public void setClearanceForm(ClearanceForm clearanceForm) {
        this.clearanceForm = clearanceForm;
    }

    public ClearanceItem(String description, boolean isCleared, ClearanceForm clearanceForm) {

        this.description = description;
        this.isCleared = isCleared;
        this.clearanceForm = clearanceForm;
    }

    public ClearanceItem() {
    }
}
