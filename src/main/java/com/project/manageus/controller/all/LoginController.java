package com.project.manageus.controller.all;

import com.project.manageus.dto.MemberDTO;
import com.project.manageus.dto.MemberInfoDTO;
import com.project.manageus.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login() {
        String url = "/all/login/login.html";
        return url;
    }

    @GetMapping("/register")
    public String register() {
        String url = "/all/login/register.html";
        return url;
    }

    @PostMapping("/register")
    public String registerPro(MemberDTO memberDTO, MemberInfoDTO memberInfoDTO, String repeatPassword, String inviteCode) {
        String url = "redirect:/register";

        if(memberDTO.getPw().equals(repeatPassword)) {
            boolean isCreated = loginService.createMember(memberDTO, memberInfoDTO, inviteCode);
            if(isCreated) {
                url = "redirect:/login";
            }
        }
        return url;
    }

    @GetMapping("/company")
    public String Company() {
        String url = "/all/login/company-register.html";
        return url;
    }

    @GetMapping("/forgot")
    public String forgot() {
        String url = "/all/login/forgot.html";
        return url;
    }

}
