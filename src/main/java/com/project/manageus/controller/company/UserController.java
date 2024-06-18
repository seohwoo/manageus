package com.project.manageus.controller.company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/api/*")
public class UserController {

    @GetMapping("/")
    public String main() {
        String url = "/company/main.html";
        return url;
    }

    @GetMapping("/test")
    public String test(Principal principal, Model model) {
        String url = "/super/main.html";
        model.addAttribute("id", principal.getName());



        return url;
    }

}
