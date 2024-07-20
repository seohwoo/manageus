package com.project.manageus.service;

import com.project.manageus.dto.MessageDTO;
import com.project.manageus.entity.MessageEntity;
import org.springframework.ui.Model;

import java.util.List;


public interface MessageService {


    public void sendmessage(Long userId, Model model); // 보낸메세지 찾기

    public void getmessage(Long userId, Model model);  //받은 메세지 찾기 아직안함

    public void messageing(MessageDTO messagedto); // 메세지 보내기 실행




}
