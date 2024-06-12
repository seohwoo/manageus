package com.project.manageus.controller.user;

import com.project.manageus.dto.ChatMessageDTO;
import com.project.manageus.service.ChatServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
public class ChatController {

    @Autowired
    private ChatServiceImpl service;

    @GetMapping("/chat")
    public String chatting(Model model, HttpSession session){
        log.info("@ChatController, chat GET()");
        int x = service.count();
        System.out.println("==========" + x);

        session.setAttribute("memberId", "testname");
        return "/user/chat/chater";
    }
    @GetMapping("/chatRoomList")
    public String chatRoomList(){
        return "/user/chat/chatRoomList";
    }
    @PostMapping("/chat")
    public String chatRoomCreate(){
        System.out.println("채팅방 생성!!");
        return "redirect:/chatRoomList";
    }
    @PostMapping("/send-message")
    @ResponseBody
    public ResponseEntity<String> sendMessage(ChatMessageDTO message) {
        // 메시지 처리 (예: 다른 사용자에게 방송, 데이터베이스에 저장 등)
        System.out.println("Received message from " + message.getMemberId() + ": " + message.getMessage());
        // 여기에서 메시지를 데이터베이스에 저장하거나, 다른 클라이언트에 방송하는 등의 로직을 추가할 수 있습니다.
        return ResponseEntity.ok("Message received");
    }
}
