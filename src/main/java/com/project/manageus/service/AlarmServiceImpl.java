package com.project.manageus.service;


import com.project.manageus.dto.AlarmDTO;
import com.project.manageus.entity.AlarmEntity;

import com.project.manageus.entity.UserInfoEntity;
import com.project.manageus.repository.AlarmJPARepository;
import com.project.manageus.repository.UserInfoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlarmServiceImpl implements AlarmService{

    private final AlarmJPARepository alarmJPARepository;

    private final UserInfoRepository userInfoRepository;

    public AlarmServiceImpl(AlarmJPARepository alarmJPARepository, UserInfoRepository userInfoRepository) {
        this.alarmJPARepository = alarmJPARepository;
        this.userInfoRepository = userInfoRepository;
    }


    @Override
    public void insert(AlarmDTO alarmDTO) {   //쪽지 쓰기
        alarmJPARepository.save(alarmDTO.toAlarmEntity());
    }



    @Override
    public void spendalarm(Long userId, Model model) {  //보낸 내역 전체가져오기

        Sort sort = Sort.by(Sort.Order.desc("readDate"));
        List<AlarmEntity> spend= alarmJPARepository.findByUserId(userId, sort); //김지환이 보낸 메세지를 찾음

        model.addAttribute("spend",spend);

    }

    @Override
    public void receive(Long userId, Model model) {  //받은 내역 전체가져오기

        Sort sort = Sort.by(Sort.Order.desc("readDate"));
        List<AlarmEntity> receive = alarmJPARepository.findByReader(userId, sort);



        model.addAttribute("receive", receive);

    }

    @Override
    public void readreceive(Long messageId, Model model) {  //받은 내용 상세보기

        Optional<AlarmEntity> optionalAlarm = alarmJPARepository.findById(messageId); //번호에 맞는 메세지 가져오기

        if (optionalAlarm.isPresent()) {
            AlarmEntity alarm = optionalAlarm.get();
            //System.out.println("Alarm found: " + alarm.toString());  // 선택된 값 확인
            model.addAttribute("alarm", alarm);


            Long userId = alarm.getReader();
            Optional<UserInfoEntity> optionalUserInfo = userInfoRepository.findById(userId);
            UserInfoEntity userInfo = optionalUserInfo.get();  // 해당 회원번호에 맞는 회원 이름 가져오기위해 작업
            model.addAttribute("userName", userInfo.getName());  //번호에 맞는 이름 가져오기
        } else {
            System.out.println("No alarm found with ID: " + messageId);
            model.addAttribute("error", "Message not found");
        }
    }

    @Override
    public void readspend(Long messageId, Model model) { //보낸내용 상세보기
        
        Optional<AlarmEntity> optionalAlarm = alarmJPARepository.findById(messageId); //번호에 맞는 레코드추출
        if (optionalAlarm.isPresent()) {
            AlarmEntity alarm = optionalAlarm.get();
            //System.out.println("Alarm found: " + alarm.toString());  // 선택된 값 확인
            model.addAttribute("alarm", alarm);


            Long receiveId = alarm.getUserId();    //보낸 쪽지함에서 받은사람의 이름을 가져올때
            Optional<UserInfoEntity> optionalUserInfo = userInfoRepository.findById(receiveId);
            UserInfoEntity userInfo = optionalUserInfo.get();  // 해당 회원번호에 맞는 회원 이름 가져오기위해 작업
            model.addAttribute("userName", userInfo.getName());  //번호에 맞는 이름 가져오기

            Long spendId = alarm.getReader(); //보낸 쪽지함에서 보낸사람의 이름을 가져올때
            Optional<UserInfoEntity> spendoptionalUserInfo = userInfoRepository.findById(spendId);
            UserInfoEntity spenduserInfo = spendoptionalUserInfo.get();  // 해당 회원번호에 맞는 회원 이름 가져오기위해 작업
            model.addAttribute("spendName", spenduserInfo.getName());  //번호에 맞는 이름 가져오기


        } else {
            System.out.println("No alarm found with ID: " + messageId);
            model.addAttribute("error", "Message not found");
        }


    }


}
