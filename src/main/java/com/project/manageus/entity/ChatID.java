package com.project.manageus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatID implements Serializable {
    private Long user_id;
    private Long chat_room_id;
}
