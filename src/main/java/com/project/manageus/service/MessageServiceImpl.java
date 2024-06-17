package com.project.manageus.service;

import com.project.manageus.dto.MessageDTO;
import com.project.manageus.entity.MessageEntity;
import com.project.manageus.repository.MessageJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void sendmessage(int userId, Model model) { //보낸 메시지 전체
        System.out.println("Session userId: 출력확인" + userId);
        model.addAttribute( "messageJPA" ,messageJPA.sendmessage(userId));
    }

    @Override
    public void getmessage(int userId, Model model) { //받은 메세지 전체
        System.out.println("Session userId: 출력확인" + userId);
        model.addAttribute( "messageJPA" ,messageJPA.getmessage(userId));
    }

    @Override
    public void messageing(MessageDTO messagedto, Model model) { //메세지 보내기 실행
        try {
            int result = messageJPA.messageing(messagedto);
            if (result == 1) {
                // 성공적으로 삽입된 경우의 로직
                System.out.println("Message inserted successfully.");
                model.addAttribute("result",result);
            } else {
                // 이론적으로 여기에 도달하지 않음 (INSERT 쿼리는 항상 영향을 받는 행이 있어야 함)
                System.out.println("Message insertion affected 0 rows, which is unexpected for INSERT.");
                model.addAttribute("result",result);
            }
        } catch (Exception e) {
            // 예외 발생 시 처리 로직
            System.out.println("Message insertion failed: " + e.getMessage());
            // 예외를 던지거나, 다른 예외 처리 로직을 여기에 추가할 수 있습니다.
        }
    }


}
