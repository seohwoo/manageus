package com.project.manageus.dto;

import com.project.manageus.entity.ChatEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatDTO {
    private Long userId;
    private Long chatRoomId;


    @Builder
    public ChatDTO(Long userId, Long chatRoomId){
        this.userId = userId;
        this.chatRoomId = chatRoomId;

    }
    public ChatEntity toChatEntity() {
        return ChatEntity.builder()
                .userId(this.userId)
                .chatRoomId(this.chatRoomId)
                .build();
    }
}
