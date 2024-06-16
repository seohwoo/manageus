package com.project.manageus.service;

import com.project.manageus.dto.ChatDTO;
import com.project.manageus.dto.ChatRoomDTO;
import com.project.manageus.entity.ChatEntity;
import com.project.manageus.entity.ChatRoomEntity;
import com.project.manageus.repository.ChatJPARepository;
import com.project.manageus.repository.ChatRoomJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {


    private final ChatJPARepository chatJPA;


    private final ChatRoomJPARepository chatRoomJPA;

    @Autowired
    public ChatServiceImpl(ChatJPARepository chatJPA,ChatRoomJPARepository chatRoomJPA){
        this.chatJPA=chatJPA;
        this.chatRoomJPA=chatRoomJPA;
    }

    public int count(){

        return (int)chatJPA.count();

    }
    @Override
    public void chatNewRoom(ChatRoomDTO dto,Long id){
        System.out.println("id=="+dto.getId());
        System.out.println("name=="+dto.getName());
        System.out.println("reg=="+dto.getReg());
        System.out.println("status=="+dto.getStatusId());
        ChatDTO cdto = new ChatDTO();

        chatRoomJPA.save(dto.toChatRoomEntity());
        List<ChatRoomEntity> list= chatRoomJPA.findAll();
        ChatRoomEntity max = Collections.max(list, Comparator.comparingLong(ChatRoomEntity::getId));
        Long nextid=max.getId();
        cdto.setChatRoomId(nextid);
        System.out.println("cdto.setChatRoomId(nextid)"+nextid);
        cdto.setUserId(id);
        System.out.println("cdto.setUserId(id)"+id);
        chatJPA.save(cdto.toChatEntity());
    }

}
