package com.project.manageus.controller.company;

import com.project.manageus.dto.ChatDTO;
import com.project.manageus.dto.ChatMessageDTO;
import com.project.manageus.dto.ChatRoomDTO;
import com.project.manageus.service.ChatService;
import com.project.manageus.service.ChatServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
public class ChatController {

    private final ChatService service;

    @Autowired
    public ChatController(ChatService service){
        this.service=service;
    }

    @GetMapping("/company/1111/chat/{id}/{roomId}")
    public String chatting(Model model, HttpSession session,@PathVariable(value = "id") Long id,@PathVariable(value = "roomId")Long roomId){

        service.enterChatRoom(model,id,roomId);
        return "/company/chat/chater";
    }

    @GetMapping("/company/1111/chatRoomList")
    public String chatRoomList(HttpSession session,Model model){
        session.setAttribute("memberId", "10010001");
        String sid = (String)session.getAttribute("memberId");
        Long id = Long.parseLong(sid);
        service.chatList(model,id);
        return "/company/chat/chatRoomList";
    }
    @PostMapping("/company/1111/chat")
    public String chatRoomCreate(HttpSession session,ChatRoomDTO dto){
        System.out.println("채팅방 생성!!");
       String id = (String)session.getAttribute("memberId");
        Long idl = Long.parseLong(id);
        System.out.println("id===="+idl);
        Long roomId=service.chatNewRoom(dto,idl);
        String url="redirect:/company/1111/chat/"+idl+"/"+roomId;

        return url;
    }
    @DeleteMapping("/company/1111/chat")
    public String chatRoomExit(ChatDTO dto){
        service.chatExit(dto);
        return "redirect:/company/chat/chatRoomList";
    }

    @PostMapping("/send-message")
    @ResponseBody
    public ResponseEntity<String> sendMessage(ChatMessageDTO message) {
        // 메시지 처리 (예: 다른 사용자에게 방송, 데이터베이스에 저장 등)
        System.out.println("Received message from " + message.getUserId() + ": " + message.getMessage());
        // 여기에서 메시지를 데이터베이스에 저장하거나, 다른 클라이언트에 방송하는 등의 로직을 추가할 수 있습니다.
        return ResponseEntity.ok("Message received");
    }


}
