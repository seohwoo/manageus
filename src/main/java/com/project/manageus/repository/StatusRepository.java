package com.project.manageus.repository;

import com.project.manageus.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
