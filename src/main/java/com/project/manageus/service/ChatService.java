package com.project.manageus.service;

import com.project.manageus.dto.ChatDTO;
import com.project.manageus.dto.ChatRoomDTO;
import org.springframework.ui.Model;

public interface ChatService {
    public Long chatNewRoom(ChatRoomDTO dto,Long id);
    public void chatList(Model model,Long id);
    public void enterChatRoom(Model model,Long id,Long roomId);
    public void chatExit(ChatDTO dto);
}
