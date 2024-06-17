package com.project.manageus.dto;

import com.project.manageus.entity.ChatEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatDTO {
    private Long user_id;
    private Long chat_room_id;

    @Builder
    public ChatDTO(Long user_id, Long chat_room_id){
        this.user_id = user_id;
        this.chat_room_id=chat_room_id;
    }
    public ChatEntity toChatEntity() {
        return ChatEntity.builder()
                .user_id(this.user_id)
                .chat_room_id(this.chat_room_id)
                .build();
    }
}
