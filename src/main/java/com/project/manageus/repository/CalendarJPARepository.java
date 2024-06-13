package com.project.manageus.repository;

import com.project.manageus.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarJPARepository extends JpaRepository <CalendarEntity, Integer> {

   //여기에 sql문장을 적는다


}
