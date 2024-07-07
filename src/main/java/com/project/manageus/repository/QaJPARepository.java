package com.project.manageus.repository;

import com.project.manageus.entity.QaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QaJPARepository extends JpaRepository<QaEntity,Long> {
    public List<QaEntity> findByIdOrRef(Long id,Long ref);
    public int countByRef(Long ref);
    public int countByIdOrRef(Long id,Long ref);
    public Page<QaEntity> findByRef(Long ref, Pageable pageable);
}
