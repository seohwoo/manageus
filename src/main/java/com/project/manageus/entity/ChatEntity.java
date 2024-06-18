package com.project.manageus.entity;

import com.project.manageus.dto.ChatDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="chat")
@IdClass(ChatIDEntity.class)
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
    public ChatDTO toChatDTO() {
        return ChatDTO.builder()
                .userId(this.userId)
                .chatRoomId(this.chatRoomId)
                .build();
    }
}
