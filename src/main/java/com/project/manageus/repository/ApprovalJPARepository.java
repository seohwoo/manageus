package com.project.manageus.repository;

import com.project.manageus.entity.ApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalJPARepository extends JpaRepository<ApprovalEntity, Integer> {
    // 여기에 SQL 적으면됨

}
