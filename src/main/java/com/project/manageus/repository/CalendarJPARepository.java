package com.project.manageus.repository;

import com.project.manageus.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarJPARepository extends JpaRepository <CalendarEntity, Integer> {


   public List<CalendarEntity> findByUserId(Long id);

}
