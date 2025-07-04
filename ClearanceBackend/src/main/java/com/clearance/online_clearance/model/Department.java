package com.clearance.online_clearance.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@JsonIgnoreProperties({ "students" })
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Student> students;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
   @JsonIgnore
    private List<HOD> hods;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<ClearanceForm> clearanceForms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<HOD> getHods() {
        return hods;
    }

    public void setHods(List<HOD> hods) {
        this.hods = hods;
    }

    public List<ClearanceForm> getClearanceForms() {
        return clearanceForms;
    }

    public void setClearanceForms(List<ClearanceForm> clearanceForms) {
        this.clearanceForms = clearanceForms;
    }

    public Department(String name, List<Student> students, List<HOD> hods,
            List<ClearanceForm> clearanceForms) {

        this.name = name;
        this.students = students;
        this.hods = hods;
        this.clearanceForms = clearanceForms;
    }

    public Department() {
    }

}
