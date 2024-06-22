package com.project.manageus.controller.company;

import com.project.manageus.dto.AlarmDTO;
import com.project.manageus.service.AlarmService;
import com.project.manageus.service.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/alarm/{id}/form")  // 쪽지 작성하는 곳
    public String write(Model model, Principal principal, @PathVariable Long companyId,
                        @PathVariable Long id) {


        if (!urlService.findUserInfo(principal.getName(), companyId, model)
                || id != Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }

        return "/company/alarm/write";
    }

    // 포스트 매핑 처리해야됨

    @PostMapping("/alarm/{id}/formpro")
    public String writepro(Principal principal, AlarmDTO alarmDTO,@PathVariable Long companyId,
                           @PathVariable Long id,@RequestParam("reader") String reader,
                           @RequestParam("subject") String subject){


        Long readtype = 2000L;
        Long userId = Long.parseLong(principal.getName());
         Long readers = Long.parseLong(reader);


        alarmDTO.setUserId(userId);
        alarmDTO.setReader(readers);
        alarmDTO.setSubject(subject);
        alarmDTO.setReadType(readtype);

        alarmService.insert(alarmDTO);

        return  "redirect:/companies/" + companyId + "/alarm/" + id + "/spend";
    }





    // 아래는 내가 받은 쪽지 내역
    @GetMapping("/alarm/{id}/receive")  // 받은 쪽지
    public String receive(Model model, Principal principal, @PathVariable Long companyId,
                          @PathVariable Long id) {

        Long userId = Long.parseLong(principal.getName()); // 세션 받아오기

        if (!urlService.findUserInfo(principal.getName(), companyId, model)
                || id != Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }

        alarmService.receive(userId, model);

        return "/company/alarm/receive";
    }



    // 아래는 내가 보낸 쪽지 내역
    @GetMapping("/alarm/{id}/spend") // 내가 보낸 쪽지 내역
    public String spend(Model model, Principal principal, @PathVariable Long companyId,
                        @PathVariable Long id) {

        Long userId = Long.parseLong(principal.getName()); // 세션 받아오기

        if (!urlService.findUserInfo(principal.getName(), companyId, model)
                || id != Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }

        alarmService.spendalarm(userId, model); // 받은 내역 전체 가져오기

        return "/company/alarm/spend";
    }
}
