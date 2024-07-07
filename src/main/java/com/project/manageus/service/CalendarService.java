package com.project.manageus.service;
import java.util.List;

import com.project.manageus.dto.CalendarDetailDTO;
import com.project.manageus.entity.CalendarDetailEntity;
import com.project.manageus.entity.CalendarEntity;
import org.springframework.ui.Model;

public interface CalendarService {


    public void calendarlist(Model model, Long id);

    List<CalendarEntity> getEventsByUserId(Long id);
    public CalendarEntity getCalendarId(Long userId, Long calendarType);
    public List<CalendarDetailEntity> getCalendarDetailList(Long calendarId);
    public CalendarDetailEntity addCalendarDetail(CalendarDetailDTO calendarDetailDTO);
}
