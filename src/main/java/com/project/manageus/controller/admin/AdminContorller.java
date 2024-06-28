package com.project.manageus.controller.admin;

import com.project.manageus.service.AdminService;
import com.project.manageus.service.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin/*")
public class AdminContorller {

    private final AdminService adminService;
    private final UrlService urlService;

    public AdminContorller(AdminService adminService, UrlService urlService) {
        this.adminService = adminService;
        this.urlService = urlService;
    }


    @GetMapping("/{companyId}")
    public String adminMain(@PathVariable Long companyId, Principal principal, Model model) {
        String url = "admin/main.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
        }
        return url;
    }



}
