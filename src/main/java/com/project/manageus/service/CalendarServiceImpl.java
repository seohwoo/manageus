package com.project.manageus.service;
import java.util.List;

import com.project.manageus.dto.CalendarDetailDTO;
import com.project.manageus.entity.CalendarDetailEntity;
import com.project.manageus.entity.CalendarEntity;
import com.project.manageus.repository.CalendarDetailRepository;
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
    private final CalendarDetailRepository calendarDetailRepository;


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

    @Override
    public CalendarEntity getCalendarId(Long userId, Long calendarType) {
        CalendarEntity calendarEntity = calendarJPARepository.findByUserIdAndCalendarType(userId,calendarType);
        return calendarEntity;
    }

    @Override
    public List<CalendarDetailEntity> getCalendarDetailList(Long calendarId) {
        List<CalendarDetailEntity> calendarDetailEntityList = calendarDetailRepository.findAllByCalendarId(calendarId);
        return calendarDetailEntityList;
    }

    @Override
    public CalendarDetailEntity addCalendarDetail(CalendarDetailDTO calendarDetailDTO) {
        CalendarDetailEntity calendarDetailEntity = calendarDetailRepository.save(calendarDetailDTO.toCalendarDetailEntity());
        return calendarDetailEntity;
    }


}
