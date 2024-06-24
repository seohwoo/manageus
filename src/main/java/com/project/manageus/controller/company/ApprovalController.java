package com.project.manageus.controller.company;

import com.project.manageus.dto.ApprovalDTO;
import com.project.manageus.entity.ApprovalTypeEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.service.ApprovalService;

import com.project.manageus.service.UrlService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// http://localhost:8080/companies/1003/approval/10030001/list
@Controller
@RequestMapping("/companies/{companyId}/*")
public class ApprovalController {

    private final ApprovalService approvalService;
    private final UrlService urlService;

    @Autowired
    public ApprovalController(ApprovalService approvalService, UrlService urlService) {
        this.approvalService = approvalService;
        this.urlService = urlService;
    }

    // 게시판 페이지
    @GetMapping("/approval/{id}/list")
    public String ApprovalList(@PathVariable Long companyId,
                               @PathVariable Long id,
                               Principal principal, Model model) {

        // GetMapping일 땐 필수 ~
        if(!urlService.findUserInfo(principal.getName(), companyId, model)
                || id!=Long.parseLong(principal.getName())) {
            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }
        // ~ 까지

        // 결재 리스트 가져오기
        approvalService.selectApprovalList(model, companyId);

        return "/company/approval/list";
    }

    // 휴가 신청 페이지
    @GetMapping("/approval/{id}/write")
    public String writeForm(@PathVariable Long companyId,
                            @PathVariable Long id,
                            Principal principal, Model model) {

        // GetMapping일 땐 필수 ~
        if(!urlService.findUserInfo(principal.getName(), companyId, model)
                || id!=Long.parseLong(principal.getName())) {
            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }
        // ~ 까지

        // 임시 결재 라인
        ArrayList<String> approver = new ArrayList<>();
        approver.add("장의석 부사장");
        approver.add("이도준 사장");
        approver.add("김지환 대리");
        approver.add("서정룡 전무");
        approver.add("이선민 상무");
        approver.add("서형우 부장");
        model.addAttribute("approver", approver);

        // 결재 종류 가져오기
        approvalService.selectApprovalType(model);

        return "/company/approval/write";
    }

    @PostMapping("/approval/{id}/write")
    public String writePro(@PathVariable Long companyId,
                           @PathVariable Long id,
                           Principal principal, Model model,
                           ApprovalDTO Adto) {

        // 결재 테이블 인서트
        approvalService.insertApproval(companyId, id, Adto);

        return "redirect:/companies/{companyId}/approval/{id}/list";
    }



    // 회사 번호가 같은 정보 다 가져오기
    // ajax 연습
    @GetMapping("/approval/{id}/test")
    public String aJaxTEST(@PathVariable Long companyId,
                           @PathVariable Long id,
                           Principal principal, Model model) {

        approvalService.selectDepartment(companyId, model);

        return "/company/approval/test";
    }



}


/*
id가 필요할 때는 밑에 코드들 쓰면됨 ( userId)
if(!urlService.findUserInfo(principal.getName(), companyId, model)
                || id!=Long.parseLong(principal.getName())) {
            url = "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        */
