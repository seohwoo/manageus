package com.project.manageus.controller.feature;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/*")
public class UserController {

    @GetMapping("/")
    public String main() {
        String url = "/feature/main.html";
        return url;
    }

}
