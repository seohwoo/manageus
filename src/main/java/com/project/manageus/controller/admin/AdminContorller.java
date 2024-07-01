package com.project.manageus.controller.admin;

import com.project.manageus.service.AdminService;
import com.project.manageus.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin/*")
public class AdminContorller {

    private final AdminService adminService;
    private final UrlService urlService;

    @Autowired
    public AdminContorller(AdminService adminService, UrlService urlService) {
        this.adminService = adminService;
        this.urlService = urlService;
    }

    @GetMapping("/{companyId}")
    public String adminMain(@PathVariable Long companyId, Principal principal, Model model) {
        String url = "admin/main.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        model.addAttribute("existUser", adminService.findAllEmployee(companyId, model));
        return url;
    }

    @GetMapping("{companyId}/employee")
    public String showAllEmployee(@PathVariable Long companyId, Principal principal, Model model) {
        String url = "admin/employee";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        model.addAttribute("existUser", adminService.findAllEmployee(companyId, model));
        return url;
    }

    @PatchMapping("{companyId}/employee")
    public String updateUserInfo(@PathVariable Long companyId,
                                 Long userId,
                                 Long positionId,
                                 Long departmentId,
                                 Long statusId) {
        String url = "redirect:/admin/" + companyId + "/employee";
        adminService.updateUserInfo(userId, positionId, departmentId, statusId);
        return url;
    }

    @GetMapping("{companyId}/employee/pending")
    public String showAllPendingEmployee(@PathVariable Long companyId, Principal principal, Model model) {
        String url = "admin/pending-employee";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        model.addAttribute("existUser", adminService.findAllPendingEmployee(companyId, model));
        return url;
    }

    @PatchMapping("{companyId}/employee/pending")
    public String updateStatusUser(@PathVariable Long companyId,
                                   Long userId,
                                   Long statusId) {
        String url = "redirect:/admin/" + companyId + "/employee";
        System.out.println(userId);
        System.out.println(statusId);
        if(!adminService.updateUserStatus(userId, statusId)) {
            url = "redirect:/admin/" + companyId + "/employee/pending";
        }
        return url;
    }

}
