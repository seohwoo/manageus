package com.project.manageus.service;

import com.project.manageus.dto.AttendanceDTO;
import com.project.manageus.entity.AttendanceEntity;

import java.util.Date;
import java.util.List;

public interface AttendanceService {
    public List<AttendanceEntity> getAttendanceList(Long userId);
    public AttendanceEntity inCompany(AttendanceDTO attendanceDTO);
    public int outCompany(Date endTime, Long id);
}
