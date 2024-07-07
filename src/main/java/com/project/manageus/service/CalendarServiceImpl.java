package com.project.manageus.service;
import java.util.List;
import com.project.manageus.entity.CalendarEntity;
import com.project.manageus.repository.CalendarJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarJPARepository calendarJPARepository;


    @Override
    public void calendarlist(Model model, Long id) {

        List<CalendarEntity> cal = calendarJPARepository.findByUserId(id);

        model.addAttribute("cal",cal);
    }

    /*달력 내용 에이젝스 (팀달력)*/
    @Override
    public List<CalendarEntity> getEventsByUserId(Long id) {
        return calendarJPARepository.findByUserIdWithDetail(id);
    }


}
