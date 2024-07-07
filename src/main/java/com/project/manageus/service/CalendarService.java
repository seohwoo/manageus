package com.project.manageus.service;
import java.util.List;
import com.project.manageus.entity.CalendarEntity;
import org.springframework.ui.Model;

public interface CalendarService {


    public void calendarlist(Model model, Long id);

    List<CalendarEntity> getEventsByUserId(Long id);
}
