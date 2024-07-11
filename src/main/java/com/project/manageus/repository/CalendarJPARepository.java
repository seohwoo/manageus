package com.project.manageus.repository;

import com.project.manageus.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Calendar;
import java.util.List;

public interface CalendarJPARepository extends JpaRepository <CalendarEntity, Long> {


   public List<CalendarEntity> findByUserId(Long id);


   @Query("SELECT c FROM CalendarEntity c JOIN CalendarDetailEntity cd ON c.id = cd.id WHERE c.userId = :id")
   List<CalendarEntity> findByUserIdWithDetail(Long id);

   public CalendarEntity findByUserIdAndCalendarType(Long userId, Long calendarType);

   public int countByUserIdAndCalendarType(Long userId, Long calendarType);

   public int countByDepartmentIdAndCalendarType(Long departmentId, Long calendarType);

   public CalendarEntity findByDepartmentIdAndCalendarType(Long departmentId, Long calendarType);

   public int countByCompanyIdAndCalendarType(Long companyId, Long calendarType);

   public CalendarEntity findByCompanyIdAndCalendarType(Long companyId, Long calendarType);
}
