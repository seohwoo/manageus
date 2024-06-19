package com.project.manageus.service;

import com.project.manageus.dto.ChatDTO;
import com.project.manageus.dto.ChatMessageDTO;
import com.project.manageus.dto.ChatRoomDTO;
import com.project.manageus.entity.ChatEntity;
import com.project.manageus.entity.ChatMessageEntity;
import com.project.manageus.entity.ChatRoomEntity;
import com.project.manageus.repository.ChatJPARepository;
import com.project.manageus.repository.ChatMessageJPARepository;
import com.project.manageus.repository.ChatRoomJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {


    private final ChatJPARepository chatJPA;
    private final ChatRoomJPARepository chatRoomJPA;
    private final ChatMessageJPARepository chatMessageJPA;



    @Autowired
    public ChatServiceImpl(ChatJPARepository chatJPA,ChatRoomJPARepository chatRoomJPA,ChatMessageJPARepository chatMessageJPA){
        this.chatJPA=chatJPA;
        this.chatRoomJPA=chatRoomJPA;
        this.chatMessageJPA=chatMessageJPA;

    }

    public int count(){

        return (int)chatJPA.count();

    }
    @Override
    public Long chatNewRoom(ChatRoomDTO dto,Long id){
        System.out.println("id=="+dto.getId());
        System.out.println("name=="+dto.getName());
        System.out.println("reg=="+dto.getReg());
        System.out.println("status=="+dto.getStatusId());
        ChatDTO cdto = new ChatDTO();
        ChatMessageDTO mdto = new ChatMessageDTO();
        chatRoomJPA.save(dto.toChatRoomEntity());
        List<ChatRoomEntity> list= chatRoomJPA.findAll();
        ChatRoomEntity max = Collections.max(list, Comparator.comparingLong(ChatRoomEntity::getId));
        Long nextid=max.getId();
        System.out.println("nextid"+nextid);
        mdto.setChatRoomId(nextid);
        cdto.setChatRoomId(nextid);
        System.out.println("cdto.setChatRoomId(nextid)"+nextid);
        cdto.setUserId(id);
        mdto.setUserId(id);
        chatJPA.save(cdto.toChatEntity());
        mdto.setMessage(id+"님 입장하셧습니다.");
        System.out.println("cdto.setUserId(id)"+id);
        System.out.println("======dto"+mdto.getChatRoomId());
        System.out.println("======dto"+mdto.getUserId());
        System.out.println("======dto"+mdto.getMessage());
        System.out.println("======dto"+mdto.getStatusId());
        //chatMessageJPA.save(mdto.toChatMessageEntity());


        return nextid;

    }

    @Override
    public void chatList(Model model, Long id) {
        System.out.println("id===="+id);
        List<ChatEntity> roomIdList = chatJPA.findByUserId(id);
        System.out.println("roomIdList===="+roomIdList);
        ArrayList<Long> roomIds = new ArrayList<>();
        for(ChatEntity ce:roomIdList){
           ChatDTO dto = ce.toChatDTO();
            System.out.println("Roomid===="+dto.getChatRoomId());
            roomIds.add(dto.getChatRoomId());
        }
        List<ChatRoomEntity> list = chatRoomJPA.findByIdInOrderByRegDesc(roomIds);
        model.addAttribute("list",list);
        model.addAttribute("id",id);
    }

    @Override
    public void enterChatRoom(Model model, Long id, Long roomId) {
        List list = Collections.emptyList();
        list=chatMessageJPA.findByChatRoomId(roomId);
        int count = chatJPA.countByUserIdAndChatRoomId(id,roomId);
        List<ChatMessageEntity> cmlist= Collections.emptyList();
        if(count!=0){
            ChatMessageDTO mdto = new ChatMessageDTO();
            cmlist = chatMessageJPA.findByChatRoomId(roomId);
            model.addAttribute("cmlist",cmlist);
        }

    }

    @Override
    public void chatExit(ChatDTO dto) {
        int count = chatJPA.countByChatRoomId(dto.getChatRoomId());
        if(count>1){
        chatJPA.deleteByUserIdAndChatRoomId(dto.getUserId(),dto.getChatRoomId());
        } else if (count<=1) {
            chatJPA.deleteByUserIdAndChatRoomId(dto.getUserId(),dto.getChatRoomId());
            chatRoomJPA.deleteById(dto.getChatRoomId());
            chatMessageJPA.deleteByChatRoomId(dto.getChatRoomId());
        }
    }

}
