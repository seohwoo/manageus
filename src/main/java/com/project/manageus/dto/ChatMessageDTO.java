package com.project.manageus.dto;



import com.project.manageus.entity.ChatMessageEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ChatMessageDTO {
    private int id;
    private int chatRoomId;
    private int memberId;
    private String message;
    private Date reg;
    private int chatCheck;

    @Builder
    public ChatMessageDTO(int id, int chatRoomId, String message, int memberId, Date reg, int chatCheck){
        super();
        this.id=id;
        this.chatRoomId=chatRoomId;
        this.memberId=memberId;
        this.message = message;
        this.reg=reg;
        this.chatCheck=chatCheck;
    }
    public ChatMessageEntity toChatMessageEntity(){
        return ChatMessageEntity.builder()
                .id(this.id)
                .chatRoomId(this.chatRoomId)
                .memberId(this.memberId)
                .message(this.message)
                .reg(this.reg)
                .chatCheck(this.chatCheck)
                .build();
    }
}
