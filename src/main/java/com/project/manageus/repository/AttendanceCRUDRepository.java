package com.project.manageus.repository;

import com.project.manageus.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface AttendanceCRUDRepository extends CrudRepository<AttendanceEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE AttendanceEntity a SET a.endTime = :endTime WHERE a.id = :id")
    public int updateEndTimeById(@Param("endTime") Date endTime, @Param("id") Long id);
}