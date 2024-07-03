package com.project.manageus.service;

import com.project.manageus.repository.CalendarJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarJPARepository calendarJPARepository;




}
