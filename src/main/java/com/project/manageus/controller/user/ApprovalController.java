package com.project.manageus.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/*")
public class ApprovalController {

    // 결재 게시판
    @GetMapping("/write")
    public String write(Model model) {

        ArrayList<String> approver = new ArrayList<>();
        approver.add("장의석 부사장");
        approver.add("이도준 사장");
        approver.add("김지환 대리");
        approver.add("서정룡 전무");
        approver.add("이선민 상무");
        approver.add("서형우 부장");

        model.addAttribute("approver", approver);

        return "/approval/write.html";
    }
}
