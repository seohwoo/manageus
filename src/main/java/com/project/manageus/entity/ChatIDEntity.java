package com.project.manageus.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatIDEntity implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "chat_room_id")
    private Long chatRoomId;
}
