package com.project.manageus.repository;

import com.project.manageus.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    // 정룡
    // 회사 부서 가져오기
    List<DepartmentEntity> findAllByCompanyId(Long companyId);
}
