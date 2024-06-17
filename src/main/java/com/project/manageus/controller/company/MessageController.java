package com.project.manageus.controller.user;

import com.project.manageus.dto.MessageDTO;
import com.project.manageus.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.manageus.entity.MessageEntity;

import java.util.List;

@Controller
@RequestMapping("/user/*")
public class MessageController {



    private final MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }



    @GetMapping("/message")  //쪽지 쓰는곳
    public String message(Model model, HttpSession session) {

        session.setAttribute("userId", 10010002);

        return "/all/message/messages.html";
    }

    @PostMapping("/message") //쪽지 쓰는곳
    public String postMessage(Model model, HttpSession session,
                              @RequestParam("reader") String reader,
                              @RequestParam("subject") String subject, MessageDTO messagedto) {

        int userId = (Integer) session.getAttribute("userId"); // 세션 아이디값 주기

        messagedto.setUserID((long) userId);
        messagedto.setReader(Long.valueOf(reader));

        service.sendmessage (userId, model);  //보낸 메세지 전체가져오기
        service.messageing(messagedto, model); //메세지 보내기 실행



        model.addAttribute("reader", reader);
        model.addAttribute("subject", subject);


        return "/all/message/massagetest";

    }

    @GetMapping("/massagetest")  // 쪽지 테스트용
    public String massagetest(Model model) {

        return "/all/message/massagetest.html";
    }



    //여기는 받은 쪽지함

    @GetMapping("/getMassage")   //받은 전체 목록
    public String getMassage(Model model, HttpSession session) {

        int userId = (Integer) session.getAttribute("userId");
        service.getmessage (userId, model);

        return "/all/message/getMassage";
    }


    //여기는 보낸 쪽지함
    @GetMapping("/sendMassage")   //보낸 전체 목록
    public String sendMassage(Model model, HttpSession session) {

        int userId = (Integer) session.getAttribute("userId");
        service.sendmessage (userId, model);

        return "/all/message/sendMassage";
    }


}
