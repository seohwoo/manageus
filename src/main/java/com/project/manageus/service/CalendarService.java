package com.project.manageus.service;
import java.util.List;

import com.project.manageus.dto.CalendarDTO;
import com.project.manageus.dto.CalendarDetailDTO;
import com.project.manageus.entity.CalendarDetailEntity;
import com.project.manageus.entity.CalendarEntity;
import com.project.manageus.entity.UserEntity;
import org.springframework.ui.Model;

public interface CalendarService {


    public void calendarlist(Model model, Long id);

    List<CalendarEntity> getEventsByUserId(Long id);
    public CalendarEntity getMyCalendarId(Long userId, Long calendarType);
    public List<CalendarDetailEntity> getCalendarDetailList(Long calendarId);
    public CalendarDetailEntity addCalendarDetail(CalendarDetailDTO calendarDetailDTO);
    public int myCalendarCount(Long userId, Long calendarType);
    public UserEntity getUserInfo(Long userId);
    public void addMyCalendar(CalendarDTO calendarDTO);
    public int teamCalendarCount(Long departmentId, Long calendarType);
    public CalendarEntity getDepartmentCalendarId(Long departmentId, Long calendarType);
    public int companyCalendarCount(Long companyId, Long calendarType);
    public CalendarEntity getCompanyCalendarId(Long companyId, Long calendarType);
}
