package com.project.manageus.controller.admin;

import com.project.manageus.service.AdminService;
import com.project.manageus.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor    //lombok 생성자 주입
@RequestMapping("/admin/*")
public class AdminContorller {

    private final AdminService adminService;
    private final UrlService urlService;

    @GetMapping("/{companyId}")
    public String adminMain(@PathVariable Long companyId,
                            Principal principal,
                            Model model) {
        String url = "admin/main.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        model.addAttribute("existUser", adminService.findAllEmployee(companyId, model));
        return url;
    }

    @GetMapping("{companyId}/employees")
    public String showAllEmployee(@PathVariable Long companyId,
                                  Principal principal,
                                  Model model) {
        String url = "admin/employee.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        model.addAttribute("existUser", adminService.findAllEmployee(companyId, model));
        return url;
    }

    @PatchMapping("{companyId}/employees")
    public String updateUserInfo(@PathVariable Long companyId,
                                 Long userId,
                                 Long positionId,
                                 Long departmentId,
                                 Long statusId) {
        String url = "redirect:/admin/" + companyId + "/employees";
        if(!adminService.updateUserInfo(userId, positionId, departmentId, statusId)) {
            url = "redirect:/admin/" + companyId + "/employees";
        }
        return url;
    }

    @GetMapping("{companyId}/employees/pending")
    public String showAllPendingEmployee(@PathVariable Long companyId,
                                         Principal principal,
                                         Model model) {
        String url = "admin/pending-employee.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        model.addAttribute("existUser", adminService.findAllPendingEmployee(companyId, model));
        return url;
    }

    @GetMapping("{companyId}/employees/exit")
    public String showExitUser(@PathVariable Long companyId,
                               Principal principal,
                               Model model) {
        String url = "/admin/exit-employee.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        model.addAttribute("existUser", adminService.findAllExitEmployee(companyId, model));
        return url;
    }

    @PatchMapping("{companyId}/employees/status")
    public String updateStatusUser(@PathVariable Long companyId,
                                   Long userId,
                                   Long statusId) {
        String url = "redirect:/admin/" + companyId + "/employees/pending";
        if(!adminService.updateUserStatus(userId, statusId)) {
            url = "redirect:/admin/" + companyId + "/employees/pending";
        }
        return url;
    }

}
