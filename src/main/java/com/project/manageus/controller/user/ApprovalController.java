package com.project.manageus.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class ApprovalController {

    // 결재 게시판
    @GetMapping("/list")
    public String list() {
        String url = "/approval/list.html";
        return url;
    }
}
