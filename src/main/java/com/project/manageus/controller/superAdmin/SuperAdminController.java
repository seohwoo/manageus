package com.project.manageus.controller.superAdmin;

import com.project.manageus.service.QaService;
import com.project.manageus.service.UrlService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/super")
public class SuperAdminController {

    private final QaService service;
    private final UrlService urlService;

    @Autowired
    public SuperAdminController(QaService service, UrlService urlService) {
        this.service=service;
        this.urlService = urlService;
    }
    //company admincontroller
    @GetMapping("/board")
    public String qaList(Model model, @RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "type",defaultValue = "1")int type, Principal principal) {
        if(!urlService.isValidCompany(principal.getName(), model)) {
            return "redirect:/admin/companies/" + principal.getName();
        }
        service.qaRead(model,pageNum,type);
        return "super/qa/list.html";
    }
    @GetMapping("/board/{num}")
    public String qaContent(Model model,@PathVariable(value = "num") Long num , Principal principal){
        if(!urlService.isValidCompany(principal.getName(), model)) {
            return "redirect:/admin/companies/" + principal.getName();
        }
        service.qaContent(model,num);
        return "super/qa/qaContent.html";
    }
    @GetMapping("/board/answer/{num}")
    public String qaReWrite(Model model,@PathVariable(value = "num")Long num , Principal principal){
        if(!urlService.isValidCompany(principal.getName(), model)) {
            return "redirect:/admin/companies/" + principal.getName();
        }
        model.addAttribute("num",num);
        return "super/qa/qaAnswer";
    }
    @PostMapping("/board")
    public String qaReInsert(@RequestParam(value="num")Long num
                             , @RequestParam(value="type")int type
                             , @RequestParam(value="content")String content
                             , Principal principal) throws MessagingException {

        service.qaAnswer(Long.parseLong(principal.getName()),num,content,type);
        return "redirect:/super/board";
    }

}
