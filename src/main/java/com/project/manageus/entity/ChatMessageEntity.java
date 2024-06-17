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
    @Column(name = "member_id")
    private Long memberId;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reg;
    @Column(name = "status_id")
    private Long statusId;

    @Builder
    public ChatMessageEntity(Long id, Long chatRoomId, String message, Long memberId, Date reg, Long statusId){
        super();
        this.id=id;
        this.chatRoomId= chatRoomId;
        this.memberId= memberId;
        this.message = message;
        this.reg=reg;
        this.statusId=statusId;
    }
    public ChatMessageEntity toChatMessageEntity(){
        return ChatMessageEntity.builder()
                .id(this.id)
                .chatRoomId(this.chatRoomId)
                .memberId(this.memberId)
                .message(this.message)
                .reg(this.reg)
                .statusId(this.statusId)
                .build();
    }

}
