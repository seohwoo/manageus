package com.project.manageus.controller.company;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.manageus.dto.ApprovalDTO;
import com.project.manageus.dto.DepartmentDTO;
import com.project.manageus.entity.ApprovalTypeEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.service.ApprovalService;

import com.project.manageus.service.UrlService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

// http://localhost:8080/manageus/approval/posts
@Controller
@RequestMapping("/manageus/approval/*")
public class ApprovalController {

    private final ApprovalService approvalService;
    private final UrlService urlService;

    @Autowired
    public ApprovalController(ApprovalService approvalService, UrlService urlService) {
        this.approvalService = approvalService;
        this.urlService = urlService;
    }

    // 게시판 페이지
    @GetMapping("/posts")
    public String ApprovalList(Principal principal, Model model) {
        Long companyId = Long.parseLong(urlService.findCompanyUrl(principal.getName()));
        // GetMapping일 땐 필수 ~
        if (!urlService.isValidUser(principal.getName(), model)) {
            return "redirect:/manageus/companies/"+companyId;
        }
        // ~ 까지

        // 결재 리스트 가져오기
        approvalService.selectApprovalList(model, companyId);

        return "/company/approval/list";
    }

    // 휴가 신청 페이지
    @GetMapping("/posts/new")
    public String writeForm(Principal principal, Model model) {

        Long companyId = Long.parseLong(urlService.findCompanyUrl(principal.getName()));
        // GetMapping일 땐 필수 ~
        if (!urlService.isValidUser(principal.getName(), model)) {
            return "redirect:/manageus/companies/"+companyId;
        }
        // ~ 까지

        model.addAttribute("id", principal.getName());
        model.addAttribute("companyId", companyId);
        approvalService.selectDepartment(companyId, model);

        // 결재 종류 가져오기
        approvalService.selectApprovalType(model);

        return "/company/approval/write";
    }

    //휴가 신청하기 pro
    @PostMapping("/posts")
    public String writePro(Principal principal, Model model,
                           ApprovalDTO Adto,
                           @RequestParam("personId") List<Long> personId) {

        Long companyId = Long.parseLong(urlService.findCompanyUrl(principal.getName()));
        Long id = Long.parseLong(principal.getName());

        // 결재 테이블 인서트
        approvalService.insertApproval(companyId, id, Adto, personId);

        model.addAttribute("id", id);
        model.addAttribute("companyId", companyId);
        return "redirect:/manageus/approval/posts";
    }


    @PostMapping("/posts/write")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> writeAjax(@RequestBody DepartmentDTO dto,
                                                         @RequestParam Long userId) {

        JsonObject JObject = approvalService.selectPositionPeople(dto, userId);
        Map<String, Object> MSO = new Gson().fromJson(JObject, Map.class);
        return ResponseEntity.ok(MSO);
    }


    @GetMapping("/posts/{approvalId}")
    public String info(@PathVariable Long approvalId,
                       Principal principal, Model model) {

        Long companyId = Long.parseLong(urlService.findCompanyUrl(principal.getName()));
        // GetMapping일 땐 필수 ~
        if (!urlService.isValidUser(principal.getName(), model)) {
            return "redirect:/manageus/companies/"+companyId;
        }
        // ~ 까지

        //
        Long sessionId = Long.parseLong(principal.getName());
        String sessionIds = principal.getName();
        model.addAttribute("sessionIds", sessionIds);
        model.addAttribute("sessionId", sessionId);

        // 글번호에 맞는 정보 가져오기
        approvalService.selectApprovalDetail(approvalId, sessionId, model);
        return "/company/approval/info";
    }

    @PutMapping("/posts/{approvalId}")
    public String approvalUpdate(@PathVariable Long approvalId, Principal principal) {
        Long id = Long.parseLong(principal.getName());
        approvalService.approvalUpdate(approvalId, id);
        return "redirect:/manageus/approval/posts/"+approvalId;
    }

    @PutMapping("/posts/{approvalId}/reject")
    public String approvalReject(@PathVariable Long approvalId, Principal principal,
                                 @RequestParam("status") Long status) {
        System.out.println(approvalId+"------------------approvalId---------------------------------------------");
        System.out.println(status+"------------------status---------------------------------------------");

        approvalService.approvalReject(approvalId, status);
        //Long id = Long.parseLong(principal.getName());
        //approvalService.approvalUpdate(approvalId, id);
        return "redirect:/manageus/approval/posts/"+approvalId;
    }
}

/*
    // 회사 번호가 같은 정보 다 가져오기
    // ajax 연습
    @GetMapping("/approval/{id}/test")
    public String aJaxTEST(@PathVariable Long companyId,
                           @PathVariable Long id,
                           Principal principal, Model model) {

        approvalService.selectDepartment(companyId, model);

        return "/company/approval/test";
    }

    @PostMapping("/approval/test2")
    public String aJaxTEST2(@PathVariable Long companyId,
                           @PathVariable Long id,
                           Principal principal, Model model,
                            @RequestParam Long departmentId) {

        System.out.println("Selected department ID: " + departmentId);

        return "/company/approval/test2";
    }
*/






/*
id가 필요할 때는 밑에 코드들 쓰면됨 ( userId)
if(!urlService.findUserInfo(principal.getName(), companyId, model)
                || id!=Long.parseLong(principal.getName())) {
            url = "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        */
