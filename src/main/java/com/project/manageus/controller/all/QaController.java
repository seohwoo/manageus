package com.project.manageus.controller.all;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QaController {

    @GetMapping("/board/write")
    public String qaWrite() {
        return "all/qa/write.html";
    }

    @PostMapping("/board")
    public String qaInsert() {
        System.out.println("test1`111111");
        return "all/qa/list.html";
        // return "redirect:/board/12";
    }

    @GetMapping("/board")
    public String qaList() {
        return "qa/list.html";
    }
}

