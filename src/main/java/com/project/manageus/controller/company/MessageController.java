package com.project.manageus.controller.company;

import com.project.manageus.dto.MessageDTO;
import com.project.manageus.service.MessageService;
import com.project.manageus.service.UrlService;
import jakarta.servlet.http.HttpSession;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.manageus.entity.MessageEntity;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/companies/{companyId}/*")
public class MessageController {


    private final MessageService service;
    private final UrlService urlService;

    @Autowired
    public MessageController(MessageService service, UrlService urlService) {
        this.service = service;
        this.urlService = urlService;
    }

    //@PathVariable Long companyId,    @PathVariable Long id,   //url 속 세션을 받아올 때
    

    @GetMapping("/messages/{id}/form")  //쪽지 쓰는곳
    public String message(Model model, Principal principal, Long companyId, Long id) {

        Long userId = Long.parseLong( principal.getName()); // 세션받아오기

        if(!urlService.findUserInfo(principal.getName(), companyId, model)
                || id!=Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }

        return "/company/message/messages.html";
    }

    @PostMapping("/messages/{id}") //쪽지 쓰는곳
    public String postMessage(Model model, HttpSession session,
                              @RequestParam("reader") String reader,
                              @RequestParam("subject") String subject, MessageDTO messagedto) {

        // 세션에서 userId를 Long으로 가져오기
        Long userId = (Long) session.getAttribute("userId");

        Long spendId = 10010002L;//수신자 정룡이 임의값
        Long readtype = 0L; // 안읽음

        messagedto.setUserId(userId);
        messagedto.setReader(spendId);
        messagedto.setReadType(readtype);

       service.sendmessage (userId, model);  //보낸 메세지 전체가져오기
       service.messageing(messagedto); //메세지 보내기 실행

        model.addAttribute("reader", reader);
        model.addAttribute("subject", subject);

        return "/company/message/massagetest";

    }

    @GetMapping("/massagetest")  // 쪽지 테스트용
    public String massagetest(Model model) {

        return "/company/message/massagetest.html";
    }



    //여기는 받은 쪽지함

    @GetMapping("/massages/{id}/받음")   //받은 전체 목록
    public String getMassage(Model model, HttpSession session, MessageDTO messagedto) {

        Long userId = (Long) session.getAttribute("userId"); //유저아이디는 지환


        service.getmessage (userId, model);

        return "/company/message/getMassage";
    }





    //여기는 보낸 쪽지함
    @GetMapping("/massages/{id}/보냄")   //보낸 전체 목록
    public String sendMassage(Model model, HttpSession session, MessageDTO messagedto) {

        Long userId = (Long) session.getAttribute("userId");
        service.sendmessage (userId, model);

        return "/company/message/sendMassage";
    }


}
