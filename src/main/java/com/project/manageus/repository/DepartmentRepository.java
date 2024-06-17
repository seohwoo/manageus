package com.project.manageus.repository;

import com.project.manageus.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
