package com.project.manageus.dto;



import com.project.manageus.entity.ChatMessageEntity;
import lombok.Builder;

import java.util.Date;


public class ChatMessageDTO {
    private int id;
    private int chatRoomId;
    private int memberId;
    private String content;
    private Date reg;
    private int chatCheck;

    @Builder
    public ChatMessageDTO(int id,int chatRoomId,String content,int memberId,Date reg,int chatCheck){
        super();
        this.id=id;
        this.chatRoomId=chatRoomId;
        this.memberId=memberId;
        this.content=content;
        this.reg=reg;
        this.chatCheck=chatCheck;
    }
    public ChatMessageEntity toChatMessageEntity(){
        return ChatMessageEntity.builder()
                .id(this.id)
                .chatRoomId(this.chatRoomId)
                .memberId(this.memberId)
                .content(this.content)
                .reg(this.reg)
                .chatCheck(this.chatCheck)
                .build();
    }
}
