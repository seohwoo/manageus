package com.project.manageus.service;


import com.project.manageus.dto.AlarmDTO;
import com.project.manageus.entity.AlarmEntity;

import com.project.manageus.repository.AlarmJPARepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService{

    private final AlarmJPARepository alarmJPARepository;

    public AlarmServiceImpl(AlarmJPARepository alarmJPARepository) {
        this.alarmJPARepository = alarmJPARepository;
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

        model.addAttribute("receive",receive);
    }

    @Override
    public void insert(AlarmDTO alarmDTO) {
        alarmJPARepository.save(alarmDTO.toAlarmEntity());
    }


}
