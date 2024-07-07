package com.project.manageus.controller.all;

import com.project.manageus.dto.QaDTO;
import com.project.manageus.service.QaService;
import com.project.manageus.service.QaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QaController {

    private final QaService service;


    @Autowired
    public QaController(QaService service) {
        this.service = service;
    }


    @PostMapping("/board")
    public String qaInsert(QaDTO dto, @RequestParam(value="ref",defaultValue = "0")Long ref) {
        dto.setRef(ref);
        service.qaWrite(dto);
        return "all/qa/success.html";
    }



}

