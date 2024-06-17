package com.project.manageus.controller.user;

import jakarta.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class ProjectController {

    @GetMapping("/project")
    public String main() {

        return "/project/main.html";
    }

    @GetMapping("/project/detail")
    public String detail() {

        return "/project/main.html";
    }
}
