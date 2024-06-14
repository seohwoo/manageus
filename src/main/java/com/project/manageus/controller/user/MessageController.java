package com.project.manageus.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class MessageController {

    @GetMapping("/message")
    public String message() {
        String url = "/all/message/messages.html";
        return url;
    }

}
