package com.project.manageus.dto;



import com.project.manageus.entity.ChatMessageEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ChatMessageDTO {
    private Long id;
    private Long chatRoomId;
    private Long memberId;
    private String message;
    private Date reg;
    private Long statusId;

    @Builder
    public ChatMessageDTO(Long id, Long chatRoomId, String message, Long memberId, Date reg, Long statusId){
        super();
        this.id=id;
        this.chatRoomId=chatRoomId;
        this.memberId=memberId;
        this.message = message;
        this.reg=reg;
        this.statusId = statusId;
    }
    public ChatMessageEntity toChatMessageEntity(){
        return ChatMessageEntity.builder()
                .id(this.id)
                .chatRoomId(this.chatRoomId)
                .memberId(this.memberId)
                .message(this.message)
                .reg(this.reg)
                .statusId(this.statusId)
                .build();
    }
}
