package com.project.manageus.service;

import com.google.gson.JsonObject;
import com.project.manageus.dto.ChatCheckDTO;
import com.project.manageus.dto.ChatDTO;
import com.project.manageus.dto.ChatMessageDTO;
import com.project.manageus.dto.ChatRoomDTO;
import org.springframework.ui.Model;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface ChatService {
    public Long chatNewRoom(ChatRoomDTO dto,Long id);
    public void chatList(Model model,Long id);
    public void enterChatRoom(Model model,Long id,Long roomId);
    public void chatExit(ChatDTO dto);
    public void sendMessage(ChatMessageDTO dto);
    public void chatInvitations(Model model,Long companyId);
    public JsonObject getNamesfromDepartment(ChatRoomDTO dto);
    public void userInvitation(ChatDTO dto);
    public void changRoomName(Long roomId,String newRoomName);
    public void checkLastTime(ChatDTO dto);
    public List<ChatCheckDTO> chatAlarm(Long id);
}
