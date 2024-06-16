package com.project.manageus.entity;

import com.project.manageus.dto.ChatDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name="chat")
@IdClass(ChatID.class)
public class ChatEntity  {
    @Id
    private Long user_id;
    @Id
    private Long chat_room_id;

    @Builder
    public ChatEntity(Long user_id,Long chat_room_id){
        this.user_id=user_id;
        this.chat_room_id=chat_room_id;
    }
    public ChatEntity toChatEntity() {
        return ChatEntity.builder()
                .user_id(this.user_id)
                .chat_room_id(this.chat_room_id)
                .build();
    }
}
