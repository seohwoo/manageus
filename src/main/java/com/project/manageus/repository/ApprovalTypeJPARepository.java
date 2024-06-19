package com.project.manageus.repository;

import com.project.manageus.entity.ApprovalTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprovalTypeJPARepository extends JpaRepository<ApprovalTypeEntity, Integer> {

}
