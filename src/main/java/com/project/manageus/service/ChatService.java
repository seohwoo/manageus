package com.project.manageus.service;

import com.project.manageus.dto.ChatRoomDTO;

public interface ChatService {
    public void chatNewRoom(ChatRoomDTO dto,Long id);
}
