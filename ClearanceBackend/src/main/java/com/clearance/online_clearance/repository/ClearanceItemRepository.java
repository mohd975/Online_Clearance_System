package com.clearance.online_clearance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clearance.online_clearance.model.ClearanceItem;

public interface ClearanceItemRepository extends JpaRepository<ClearanceItem, Long> {

}
