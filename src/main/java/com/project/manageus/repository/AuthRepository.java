package com.project.manageus.repository;

import com.project.manageus.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
}
