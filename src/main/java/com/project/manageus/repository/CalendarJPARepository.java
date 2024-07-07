package com.project.manageus.repository;

import com.project.manageus.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalendarJPARepository extends JpaRepository <CalendarEntity, Integer> {


   public List<CalendarEntity> findByUserId(Long id);


   @Query("SELECT c FROM CalendarEntity c JOIN CalendarDetailEntity cd ON c.id = cd.id WHERE c.userId = :id")
   List<CalendarEntity> findByUserIdWithDetail(Long id);


}
