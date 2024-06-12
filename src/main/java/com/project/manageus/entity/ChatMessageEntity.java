package com.project.manageus.entity;

import com.project.manageus.dto.ChatMessageDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@Entity
@Table(name="chatmessage")
public class ChatMessageEntity {
    @Id
    private int id;
    private int chatRoomId;
    private int memberId;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reg;
    private int chatCheck;

    @Builder
    public ChatMessageEntity(int id,int chatRoomId,String message,int memberId,Date reg,int chatCheck){
        super();
        this.id=id;
        this.chatRoomId=chatRoomId;
        this.memberId=memberId;
        this.message=message;
        this.reg=reg;
        this.chatCheck=chatCheck;
    }
    public ChatMessageDTO toChatMessageDTO(){
        return ChatMessageDTO.builder()
                .id(this.id)
                .chatRoomId(this.chatRoomId)
                .memberId(this.memberId)
                .message(this.message)
                .reg(this.reg)
                .chatCheck(this.chatCheck)
                .build();
    }

}
