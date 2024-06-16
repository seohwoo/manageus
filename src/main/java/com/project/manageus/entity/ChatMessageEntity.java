package com.project.manageus.entity;

import com.project.manageus.dto.ChatMessageDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@Entity
@Table(name="chat_message")
public class ChatMessageEntity {
    @Id
    private Long id;
    private Long chat_room_id;
    private Long member_id;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reg;
    private Long status_id;

    @Builder
    public ChatMessageEntity(Long id, Long chat_room_id, String message, Long member_id, Date reg, Long status_id){
        super();
        this.id=id;
        this.chat_room_id=chat_room_id;
        this.member_id=member_id;
        this.message = message;
        this.reg=reg;
        this.status_id=status_id;
    }
    public ChatMessageEntity toChatMessageEntity(){
        return ChatMessageEntity.builder()
                .id(this.id)
                .chat_room_id(this.chat_room_id)
                .member_id(this.member_id)
                .message(this.message)
                .reg(this.reg)
                .status_id(this.status_id)
                .build();
    }

}
