package com.project.manageus.service;

import com.project.manageus.dto.ChatRoomDTO;
import com.project.manageus.entity.ChatEntity;
import com.project.manageus.entity.ChatRoomEntity;
import com.project.manageus.repository.ChatJPARepository;
import com.project.manageus.repository.ChatRoomJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatJPARepository chatJPA;

    @Autowired
    ChatRoomJPARepository chatRoomJPA;

    public int count(){

        return (int)chatJPA.count();

    }
    public void chatNewRoom(ChatRoomDTO dto){
        System.out.println("id=="+dto.getId());
        System.out.println("name=="+dto.getName());
        System.out.println("reg=="+dto.getReg());
//        System.out.println("status=="+dto.getStatus());

        chatRoomJPA.save(dto.toChatRoomEntity());
    }

}
