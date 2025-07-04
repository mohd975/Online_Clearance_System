package com.clearance.online_clearance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clearance.online_clearance.model.Approval;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {

}
