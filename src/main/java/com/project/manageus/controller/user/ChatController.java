package com.project.manageus.controller.user;

import com.project.manageus.service.ChatServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class ChatController {

    @Autowired
    private ChatServiceImpl service;

    @GetMapping("/chat")
    public String chatGET(){
        log.info("@ChatController, chat GET()");
        int x = service.count();
        System.out.println("=========="+x);
        return "/user/chat/chater";
    }
}
