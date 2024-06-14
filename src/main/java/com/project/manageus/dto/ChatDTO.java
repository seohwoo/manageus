package com.project.manageus.dto;

import com.project.manageus.entity.ChatEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatDTO {
    private int memberId;
    private int chatRoomId;

    @Builder
    public ChatDTO(int memberId,int chatRoomId){
        this.memberId=memberId;
        this.chatRoomId=chatRoomId;
    }
    public ChatEntity toChatEntity() {
        return ChatEntity.builder()
                .memberId(this.memberId)
                .chatRoomId(this.chatRoomId)
                .build();
    }
}
