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
    private int memberId;
    @Id
    private int chatRoomId;

    @Builder
    public ChatEntity(int memberId, int chatRoomId) {
        this.memberId = memberId;
        this.chatRoomId = chatRoomId;
    }

    public ChatDTO toChatDTO() {
        return ChatDTO.builder()
                .memberId(this.memberId)
                .chatRoomId(this.chatRoomId)
                .build();
    }
}
