package com.project.manageus.controller.company;


import com.project.manageus.service.AlarmService;
import com.project.manageus.service.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/companies/{companyId}/*")
public class AlarmController {

    private final AlarmService alarmService;
    private final UrlService urlService;

    public AlarmController(AlarmService alarmService, UrlService urlService) {
        this.alarmService = alarmService;
        this.urlService = urlService;
    }


    @GetMapping("/alarm/{id}/form")  //쪽지 작성 하는곳
    public String write(Model model, Principal principal, @PathVariable Long companyId,
                        @PathVariable Long id){

        Long userId = Long.parseLong( principal.getName()); // 세션받아오기

        if(!urlService.findUserInfo(principal.getName(), companyId, model)
                || id!=Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }

        return "/company/alarm/write";

    }








}
