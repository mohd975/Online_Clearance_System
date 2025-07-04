package com.clearance.online_clearance.dto;

import com.clearance.online_clearance.model.ClearanceForm;

import lombok.Data;

@Data
public class ClearanceFormDTO {
    private Long id;
    private String semester;
    private int year;
    private String status;
    private String studentName;
    private String regNumber;
    private String department;

    public ClearanceFormDTO(ClearanceForm form) {
        this.id = form.getId();
        this.semester = form.getSemester();
        this.year = form.getYear();
        this.status = form.getStatus();

        if (form.getStudent() != null) {
            this.studentName = form.getStudent().getFirstName() + " " + form.getStudent().getLastName();
            this.regNumber = form.getStudent().getRegNumber();
            if (form.getStudent().getDepartment() != null) {
                this.department = form.getStudent().getDepartment().getName();
            } else {
                this.department = "N/A";
            }
        } else {
            this.studentName = "Unknown";
            this.regNumber = "N/A";
            this.department = "N/A";
        }
    }
}
