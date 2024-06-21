package com.project.manageus.controller.company;

import com.project.manageus.dto.ApprovalDTO;
import com.project.manageus.entity.ApprovalTypeEntity;
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

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/companis/{companyId}/*")
public class ApprovalController {

    private final ApprovalService approvalService;


    @Autowired
    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    // 게시판 페이지
    @GetMapping("/list")
    public String ApprovalList(Principal principal, Long companyId, Model model) {
        /*
        if(!urlService.findUserInfo(principal.getName(), companyId, model)
                || id!=Long.parseLong(principal.getName())) {
            url = "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        */


        // 임시 세션값




        //session.setAttribute("user_id", 11111111);
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

        // 휴가 종류 가져오기
        approvalService.selectApprovalType(model);

        return "/company/approval/write";
    }

    @PostMapping("write")
    public String writePro(Model model, ApprovalDTO Adto) {


        /*
        System.out.println(Adto.getContent());
        System.out.println(Adto.getEndDate());
        System.out.println(Adto.getStartDate());
        System.out.println(Adto.getTitle());
        System.out.println(Adto.getApprovalTypeId());


        LocalDateTime now = LocalDateTime.now();
        Date signOff = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Long statusId = 1001L;
        Long Id = 10010003L;

        Adto.setId(Id);
        Adto.setUserId(Id);
        Adto.setStatusId(statusId);
        Adto.setSignOff(signOff);
        */

        approvalService.insertApproval(Adto);




        return "redirect:/company/list";
    }

    @GetMapping("test")
    public String test() {
        return "/company.approval/test";
    }

    /*
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
     */

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