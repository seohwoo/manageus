package com.project.manageus.service;

import com.project.manageus.dto.MessageDTO;
import com.project.manageus.entity.MessageEntity;
import com.project.manageus.repository.MessageJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    private final MessageJPARepository messageJPA;

    @Autowired
    public MessageServiceImpl(MessageJPARepository messageJPA) {
        this.messageJPA = messageJPA;
    }

    @Override
    public void sendmessage(Long userId, Model model) { //보낸 메시지 전체

        Sort sort = Sort.by(Sort.Order.desc("readDate"));
        List<MessageEntity> sendmessage= messageJPA.findByUserId(userId, sort); //김지환이 보낸 메세지를 찾음

        model.addAttribute( "get" ,sendmessage);
    }

    @Override
    public void getmessage(Long userId, Model model) { //받은 메세지 전체
        Sort sort = Sort.by(Sort.Order.desc("readDate"));
        List<MessageEntity> givemessage = messageJPA.findByReader(userId, sort);

        model.addAttribute( "get" ,givemessage);
    }



    @Override
    public void messageing(MessageDTO messagedto) { //메세지 보내기 실행

        messageJPA.save(messagedto.toMessageEntity());

    }


}
