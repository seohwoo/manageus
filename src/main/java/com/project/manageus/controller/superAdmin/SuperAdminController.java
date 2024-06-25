package com.project.manageus.controller.superAdmin;

import com.project.manageus.service.QaService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SuperAdminController {

    private final QaService service;

    @Autowired
    public SuperAdminController(QaService service){
        this.service=service;
    }
    //company admincontroller
    @GetMapping("/super/1001/board")
    public String qaList(Model model, @RequestParam(value="pageNum",defaultValue = "1")int pageNum) {
        service.qaRead(model,pageNum);
        return "super/qa/list.html";
    }
    @GetMapping("/super/1001/board/{num}")
    public String qaContent(Model model,@PathVariable(value = "num") Long num){
        System.out.println("num:"+num);
        service.qaContent(model,num);
        return "super/qa/qaContent.html";
    }
    @GetMapping("/super/1001/answer/{num}")
    public String qaReWrite(Model model,@PathVariable(value = "num")Long num){
        model.addAttribute("num",num);
        return "super/qa/qaAnswer.html";
    }
    @PostMapping("/super/1001/answer/{num}")
    public String qaReInsert(@PathVariable(value="num")Long num
                             , @RequestParam(value="type")int type
                             , @RequestParam(value="content")String content
                             , HttpSession session) throws MessagingException {
        service.qaAnswer("admin",num,content,type);
        return "redirect:/super/1001/board";
    }

}
