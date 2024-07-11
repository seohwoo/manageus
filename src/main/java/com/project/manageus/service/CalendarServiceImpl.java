package com.project.manageus.service;
import java.util.Calendar;
import java.util.List;

import com.project.manageus.dto.CalendarDTO;
import com.project.manageus.dto.CalendarDetailDTO;
import com.project.manageus.entity.CalendarDetailEntity;
import com.project.manageus.entity.CalendarEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.repository.CalendarDetailRepository;
import com.project.manageus.repository.CalendarJPARepository;
import com.project.manageus.repository.UserRepository;
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
    private final UserRepository userRepository;


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
    public CalendarEntity getMyCalendarId(Long userId, Long calendarType) {
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

    @Override
    public int myCalendarCount(Long userId, Long calendarType) {
        int count = calendarJPARepository.countByUserIdAndCalendarType(userId,calendarType);
        return count;
    }

    @Override
    public UserEntity getUserInfo(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).get();
        return userEntity;
    }

    @Override
    public void addMyCalendar(CalendarDTO calendarDTO) {
        calendarJPARepository.save(calendarDTO.toCalendarEntity());
    }

    @Override
    public int teamCalendarCount(Long departmentId, Long calendarType) {
        int count = calendarJPARepository.countByDepartmentIdAndCalendarType(departmentId,calendarType);
        return count;
    }

    @Override
    public CalendarEntity getDepartmentCalendarId(Long departmentId, Long calendarType) {
        CalendarEntity calendarEntity = calendarJPARepository.findByDepartmentIdAndCalendarType(departmentId,calendarType);
        return calendarEntity;
    }

    @Override
    public int companyCalendarCount(Long companyId, Long calendarType) {
        int count = calendarJPARepository.countByCompanyIdAndCalendarType(companyId,calendarType);
        return count;
    }

    @Override
    public CalendarEntity getCompanyCalendarId(Long companyId, Long calendarType) {
        CalendarEntity calendarEntity = calendarJPARepository.findByCompanyIdAndCalendarType(companyId,calendarType);
        return calendarEntity;
    }


}
