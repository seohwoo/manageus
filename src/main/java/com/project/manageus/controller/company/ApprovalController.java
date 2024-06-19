package com.project.manageus.controller.company;

import com.project.manageus.service.ApprovalService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/company/*")
public class ApprovalController {

    // 배워
    private final ApprovalService service;

    @Autowired
    public ApprovalController(ApprovalService service) {
        this.service = service;
    }
    // 배워

    // 게시판 페이지
    @GetMapping("/list")
    public String ApprovalList(HttpSession session) {
        // 임시 세션값
        session.setAttribute("user_id", 11111111);
        //
        return "/company/approval/list";
    }
    // 휴가 신청 페이지
    @GetMapping("/write")
    public String writeForm(Model model) {
        // 임시 결재 라인
        ArrayList<String> approver = new ArrayList<>();
        approver.add("장의석 부사장");
        approver.add("이도준 사장");
        approver.add("김지환 대리");
        approver.add("서정룡 전무");
        approver.add("이선민 상무");
        approver.add("서형우 부장");
        model.addAttribute("approver", approver);
        //

        // 임시 휴가종류값
        ArrayList<String> approvalType = new ArrayList<>();
        approvalType.add("연차");
        approvalType.add("월차");
        approvalType.add("반차");
        approvalType.add("병가");
        model.addAttribute("approvalType", approvalType);
        //
        return "/company/approval/write";
    }

    @PostMapping("write")
    public String writePro(Model model,
                           @RequestParam(value = "approver") List<String> approvers,
                           @RequestParam(value = "title") String title,
                           @RequestParam(value = "approvalType") String approvalType,
                           @RequestParam(value = "start_date") @DateTimeFormat(pattern = "yy-MM-dd") Date start_date,
                           @RequestParam(value = "end_date") @DateTimeFormat(pattern = "yy-MM-dd") Date end_date,
                           @RequestParam(value = "content") String content) {

        model.addAttribute("approvers", approvers);
        model.addAttribute("title", title);
        model.addAttribute("approvalType", approvalType);
        model.addAttribute("start_date", start_date);
        model.addAttribute("end_date", end_date);
        model.addAttribute("content", content);

        return "/company/approval/test";
    }

    @GetMapping("test")
    public String test() {
        return "/company.approval/test";
    }


    /*
    // 결재 리스트
    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        session.setAttribute("userId", 11111111);
        return "/company/approval/list";
    }

    // 휴가 신청하기
    @PostMapping("/list")
    public String writeForm(Model model, HttpSession session,
                            @RequestParam("id") String id,
                            @RequestParam("pw") int pw, ApprovalDTO dto) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId != null) {
            model.addAttribute("userId", userId.intValue()); // intValue() 호출하여 값 전달
        }// else {
         //   model.addAttribute("userId", null); // 세션에 userId가 없는 경우 null 처리
        //}

        model.addAttribute("id", id);
        model.addAttribute("pw", pw);
        service.selectApproval(id, pw, model);

        // 위에는 값 가져오는거

        // 아래는 값 넣는거
        service.isnertApproval(dto);


        return "/company/approval/test";
    }

    // test
    @GetMapping("/test")
    public String test() {

        return "/company/approval/test";
    }

    /*
    // 결재 게시판
    @GetMapping("/write")
    public String write(Model model) {
    /*
        ArrayList<String> approver = new ArrayList<>();
        approver.add("장의석 부사장");
        approver.add("이도준 사장");
        approver.add("김지환 대리");
        approver.add("서정룡 전무");
        approver.add("이선민 상무");
        approver.add("서형우 부장");

        model.addAttribute("approver", approver);

        return "/company/approval/write.html";
    }

    @PostMapping("write")
    public String writePro(Model model, @RequestParam(value = "title") String title,
                           @RequestParam(value = "subject") String subject) {

        model.addAttribute("title", title);
        model.addAttribute("subject", subject);
        return "redirect:/company/approval/list";
    }

    @GetMapping("list")
    public String list(Model model, @RequestParam(value = "title") String title,
                       @RequestParam(value = "subject") String subject) {

        model.addAttribute("title", title);
        model.addAttribute("subject", subject);
        return "/company/approval/list.html";
    }
    */
}