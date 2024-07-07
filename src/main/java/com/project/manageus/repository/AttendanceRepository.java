package com.project.manageus.repository;

import com.project.manageus.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    public List<AttendanceEntity> findAllByUserId(Long userId);
}
