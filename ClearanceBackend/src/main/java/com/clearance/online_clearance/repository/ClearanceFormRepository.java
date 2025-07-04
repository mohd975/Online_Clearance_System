package com.clearance.online_clearance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clearance.online_clearance.dto.ClearanceRequestDTO;
import com.clearance.online_clearance.model.ClearanceForm;
import com.clearance.online_clearance.model.Student;

public interface ClearanceFormRepository extends JpaRepository<ClearanceForm, Long> {
    @Query("SELECT new com.clearance.online_clearance.dto.ClearanceRequestDTO(c.semester, c.year) FROM ClearanceForm c")
    List<ClearanceRequestDTO> findAllRequestSummaries();

    List<ClearanceForm> findByStudent(Student student);
}
