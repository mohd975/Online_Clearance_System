package com.clearance.online_clearance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clearance.online_clearance.dto.StudentDTO;
import com.clearance.online_clearance.model.Department;
import com.clearance.online_clearance.model.Student;
import com.clearance.online_clearance.model.Users;
import com.clearance.online_clearance.repository.DepartmentRepository;
import com.clearance.online_clearance.repository.StudentRepository;
import com.clearance.online_clearance.repository.UserRepository;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentController(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/students")
    public ResponseEntity<String> createStudent(@RequestBody StudentDTO dto) {
        if (dto.getDepartmentId() == null) {
            throw new RuntimeException("Department ID is required");
        }

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = new Student();
        student.setRegNumber(dto.getRegNumber());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setAge(dto.getAge());
        student.setDepartment(department);
        student.setUser(user);

        studentRepository.save(student);
        return ResponseEntity.ok("Student created successfully");
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found" + id));
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (studentDetails.getDepartment() == null || studentDetails.getDepartment().getId() == null) {
            throw new RuntimeException("Department ID must be provided");
        }

        student.setRegNumber(studentDetails.getRegNumber());
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setPhoneNumber(studentDetails.getPhoneNumber());
        student.setAge(studentDetails.getAge());
        Long deptId = studentDetails.getDepartment().getId();
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        student.setDepartment(department);

        studentRepository.save(student);

        return ResponseEntity.ok("Student updated successfully");
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.delete(student);
        return ResponseEntity.ok("Student deleted successfully");
    }

}
