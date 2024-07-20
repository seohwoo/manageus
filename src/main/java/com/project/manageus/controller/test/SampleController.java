package com.project.manageus.controller.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/*")
public class SampleController {

    @GetMapping("/index")
    public String index() {
        String url = "/sample/index.html";
        return url;
    }

    @GetMapping("/p404")
    public String p404() {
        String url = "/sample/404.html";
        return url;
    }

    @GetMapping("/blank")
    public String blank() {
        String url = "/sample/blank.html";
        return url;
    }

    @GetMapping("/buttons")
    public String buttons() {
        String url = "/sample/buttons.html";
        return url;
    }

    @GetMapping("/cards")
    public String cards() {
        String url = "/sample/cards.html";
        return url;
    }

    @GetMapping("/charts")
    public String charts() {
        String url = "/sample/charts.html";
        return url;
    }

    @GetMapping("/forgot")
    public String forgot_password() {
        String url = "/sample/forgot-password.html";
        return url;
    }

    @GetMapping("/login")
    public String login() {
        String url = "/sample/login.html";
        return url;
    }

    @GetMapping("/register")
    public String register() {
        String url = "/sample/register.html";
        return url;
    }

    @GetMapping("/tables")
    public String tables() {
        String url = "/sample/tables.html";
        return url;
    }

    @GetMapping("/animation")
    public String animation() {
        String url = "/sample/utilities-animation.html";
        return url;
    }

    @GetMapping("/border")
    public String border() {
        String url = "/sample/utilities-border.html";
        return url;
    }

    @GetMapping("/color")
    public String color() {
        String url = "/sample/utilities-color.html";
        return url;
    }

    @GetMapping("/other")
    public String other() {
        String url = "/sample/utilities-other.html";
        return url;
    }

}
