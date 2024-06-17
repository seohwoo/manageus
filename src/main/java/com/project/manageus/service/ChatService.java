package com.project.manageus.service;

import com.project.manageus.dto.ChatRoomDTO;
import org.springframework.ui.Model;

public interface ChatService {
    public void chatNewRoom(ChatRoomDTO dto,Long id);
    public void chatList(Model model,Long id);
}
