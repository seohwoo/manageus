package com.project.manageus.entity;

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
    @Column(name = "chat_room_id")
    private Long chatRoomId;
    @Column(name = "user_id")
    private Long userId;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reg;
    @Column(name="status_id")
    private Long statusId;

    @Builder
    public ChatMessageEntity(Long id, Long chatRoomId, String message, Long userId, Date reg, Long statusId){
        super();
        this.id=id;
        this.chatRoomId=chatRoomId;
        this.userId = userId;
        this.message = message;
        this.reg=reg;
        this.statusId =statusId;
    }
    public ChatMessageEntity toChatMessageEntity(){
        return ChatMessageEntity.builder()
                .id(this.id)
                .chatRoomId(this.chatRoomId)
                .userId(this.userId)
                .message(this.message)
                .reg(this.reg)
                .statusId(this.statusId)
                .build();
    }

}
