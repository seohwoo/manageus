package com.project.manageus.service;

import com.project.manageus.dto.AttendanceDTO;
import com.project.manageus.entity.AttendanceEntity;
import com.project.manageus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService{

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final StatusRepository statusRepository;
    private final AttendanceCRUDRepository attendanceCRUDRepository;


    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository
            , UserRepository userRepository
            , UserInfoRepository userInfoRepository
            , StatusRepository statusRepository
            , AttendanceCRUDRepository attendanceCRUDRepository){
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.statusRepository = statusRepository;
        this.attendanceCRUDRepository = attendanceCRUDRepository;
    }

    @Override
    public List<AttendanceEntity> getAttendanceList(Long userId) {
        List<AttendanceEntity> attendanceEntityList= attendanceRepository.findAllByUserId(userId);
        return attendanceEntityList;
    }

    @Override
    public AttendanceEntity inCompany(AttendanceDTO attendanceDTO) {
        AttendanceEntity attendanceEntity = attendanceRepository.save(attendanceDTO.toAttendanceEntity());
        return attendanceEntity;
    }

    @Override
    public int outCompany(Date endTime, Long id) {
        int result = attendanceCRUDRepository.updateEndTimeById(endTime,id);
        return result;
    }
}
