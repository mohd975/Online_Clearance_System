package com.clearance.online_clearance.dto;

import lombok.Data;

@Data

public class ClearanceRequestDTO {
    private String semester;
    private int year;

    public ClearanceRequestDTO(String semester, int year) {
        this.semester = semester;
        this.year = year;
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
}
