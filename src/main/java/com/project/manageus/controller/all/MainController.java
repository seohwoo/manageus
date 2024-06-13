package com.project.manageus.controller.all;

import com.project.manageus.bean.UserDTO;
import com.project.manageus.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {


    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String main() {
        String url = "/all/main.html";
        return url;
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
    public String registerPro(UserDTO dto) {

        userService.joinProcess(dto);

        String url = "redirect:/login";
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
