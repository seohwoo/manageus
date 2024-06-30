package com.project.manageus.service;

import com.google.gson.JsonObject;
import com.project.manageus.dto.AlarmDTO;
import com.project.manageus.dto.DepartmentDTO;
import org.springframework.ui.Model;

public interface AlarmService {

    public void spendalarm(Long userId, Model model); //보낸내역 전체

    public void receive(Long userId, Model model);   //받은 내역 전체
    
    public void insert(AlarmDTO alarmDTO);  //쪽지 보내기

    public void readreceive(Long messageId, Model model); //받은내역 상세보기

    public void readspend(Long messageId, Model model); //보낸내역 상세보기

    public void readcount(Long messageId); //글읽음 처리하기

    public void getdepartment(Long companyId, Model model);


    JsonObject getAlarmNameDepartment(Long companyId, Long departmentId);  //회사원 가져오기


    JsonObject getAlarmCount(Long id);  //안읽은 사람 숫자 가져오기
}
