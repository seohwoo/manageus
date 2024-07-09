package com.project.manageus.entity;

import com.project.manageus.dto.ChatDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="chat")
@IdClass(ChatIDEntity.class)
@DynamicUpdate
@DynamicInsert
public class ChatEntity  {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    @Column(name = "chat_room_id")
    private Long chatRoomId;
    @Column(name="last_time")
    @UpdateTimestamp
    private Date lastTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ChatRoomEntity chatRoom;

    @Builder
    public ChatEntity(Long userId,Long chatRoomId,Date lastTime){
        this.userId=userId;
        this.chatRoomId =chatRoomId;
        this.lastTime=lastTime;

    }
    public ChatDTO toChatDTO() {
        return ChatDTO.builder()
                .userId(this.userId)
                .chatRoomId(this.chatRoomId)
                .lastTime(this.lastTime)
                .build();
    }
}
