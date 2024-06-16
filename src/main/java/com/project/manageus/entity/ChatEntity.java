package com.project.manageus.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="chat")
@IdClass(ChatID.class)
public class ChatEntity  {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    @Column(name = "chat_room_id")
    private Long chatRoomId;

    @Builder
    public ChatEntity(Long userId,Long chatRoomId){
        this.userId=userId;
        this.chatRoomId =chatRoomId;

    }
    public ChatEntity toChatEntity() {
        return ChatEntity.builder()
                .userId(this.userId)
                .chatRoomId(this.chatRoomId)
                .build();
    }
}
