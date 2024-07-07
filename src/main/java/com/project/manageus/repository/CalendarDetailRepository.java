package com.project.manageus.repository;

import com.project.manageus.entity.CalendarDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarDetailRepository extends JpaRepository<CalendarDetailEntity, Long> {
    public List<CalendarDetailEntity> findAllByCalendarId(Long calendarId);
}
