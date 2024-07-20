package com.project.manageus.repository;

import com.project.manageus.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface AttendanceCRUDRepository extends CrudRepository<AttendanceEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE AttendanceEntity a SET a.endTime = :endTime WHERE a.id = :id")
    public int updateEndTimeById(@Param("endTime") Date endTime, @Param("id") Long id);

    @Query("SELECT COUNT(a) FROM AttendanceEntity a WHERE FUNCTION('DATE_FORMAT', a.startTime, '%Y-%m-%d') = :startTime")
    long countByStartTimeStartingWith(@Param("startTime") String startTime);

    @Query(value = "SELECT COUNT(a) FROM AttendanceEntity a WHERE a.userId = :userId AND FUNCTION('DATE_FORMAT', a.startTime, '%Y-%m-%d') = :startTime")
    Long countByUserIdAndStartTimeStartingWith(@Param("userId") Long userId, @Param("startTime") String startTime);

    @Query(value = "SELECT a FROM AttendanceEntity a WHERE  a.userId = :userId AND FUNCTION('DATE_FORMAT', a.startTime, '%Y-%m-%d') = :startTime")
    Optional<AttendanceEntity> findByUserIdAndStartTimeStartingWith(@Param("userId") Long userId, @Param("startTime") String startTime);
}