package com.project.manageus.dto;

import com.project.manageus.entity.ChatEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ChatDTO {
    private Long userId;
    private Long chatRoomId;
    private Date lastTime;



    @Builder
    public ChatDTO(Long userId, Long chatRoomId,Date lastTime){
        this.userId = userId;
        this.chatRoomId = chatRoomId;
        this.lastTime=lastTime;

    }
    public ChatEntity toChatEntity() {
        return ChatEntity.builder()
                .userId(this.userId)
                .chatRoomId(this.chatRoomId)
                .lastTime(this.lastTime)
                .build();
    }
}
