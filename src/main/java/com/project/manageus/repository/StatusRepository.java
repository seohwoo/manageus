package com.project.manageus.repository;

import com.project.manageus.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {

    List<StatusEntity> findByIdBetween(Long startId, Long endId);
}
