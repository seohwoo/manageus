package com.project.manageus.service;


import com.project.manageus.repository.AlarmJPARepository;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImpl implements AlarmService{

    private final AlarmJPARepository alarmJPARepository;

    public AlarmServiceImpl(AlarmJPARepository alarmJPARepository) {
        this.alarmJPARepository = alarmJPARepository;
    }












}
