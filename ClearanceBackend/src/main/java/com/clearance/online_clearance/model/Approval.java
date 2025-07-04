package com.clearance.online_clearance.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String comment;
    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name = "clearanceForm_id", nullable = false)
    private ClearanceForm clearanceForm;
    @ManyToOne
    @JoinColumn(name = "hod_id", nullable = false)
    private HOD hod;
       public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
    public ClearanceForm getClearanceForm() {
        return clearanceForm;
    }
    public void setClearanceForm(ClearanceForm clearanceForm) {
        this.clearanceForm = clearanceForm;
    }
    public HOD getHod() {
        return hod;
    }
    public void setHod(HOD hod) {
        this.hod = hod;
    }
       public Approval(String status, String comment, LocalDateTime timeStamp, ClearanceForm clearanceForm, HOD hod) {
        this.status = status;
        this.comment = comment;
        this.timeStamp = timeStamp;
        this.clearanceForm = clearanceForm;
        this.hod = hod;
    }
       public Approval() {
    }
}
