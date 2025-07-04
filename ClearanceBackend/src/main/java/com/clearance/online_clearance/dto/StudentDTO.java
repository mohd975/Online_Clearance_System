package com.clearance.online_clearance.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private String regNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int age;
    private Long departmentId;
    private Long userId;
}
