package com.project.manageus.controller.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/*")
public class UserController {

    @GetMapping("/")
    public String main() {
        String url = "/company/main.html";
        return url;
    }

}
