package com.clearance.online_clearance.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClearanceForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "semester", nullable = false)
    private String semester;
    @Column(name = "year", nullable = false)
    private int year;
    @Column(name = "status", nullable = false)
    private String status;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private Student student;
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnore
    private Department department;
    @OneToMany(mappedBy = "clearanceForm", cascade = CascadeType.ALL)
    private List<ClearanceItem> clearanceItems;
    @OneToMany(mappedBy = "clearanceForm", cascade = CascadeType.ALL)
    private List<Approval> approvals;

    public List<Approval> getApprovals() {
        return approvals;
    }

    public void setApprovals(List<Approval> approvals) {
        this.approvals = approvals;
    }

    public ClearanceForm(String semester, int year, String status, Student student, Department department,
            List<ClearanceItem> clearanceItems, List<Approval> approvals) {
        this.semester = semester;
        this.year = year;
        this.status = status;
        this.student = student;
        this.department = department;
        this.clearanceItems = clearanceItems;
        this.approvals = approvals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<ClearanceItem> getClearanceItems() {
        return clearanceItems;
    }

    public void setClearanceItems(List<ClearanceItem> clearanceItems) {
        this.clearanceItems = clearanceItems;
    }

    public ClearanceForm(String semester, int year, String status, Student student, Department department,
            List<ClearanceItem> clearanceItems) {
        this.semester = semester;
        this.year = year;
        this.status = status;
        this.student = student;
        this.department = department;
        this.clearanceItems = clearanceItems;
    }

}
