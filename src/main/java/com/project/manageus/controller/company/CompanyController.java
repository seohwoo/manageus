package com.project.manageus.controller.company;

import com.project.manageus.entity.UserEntity;
import com.project.manageus.service.SideBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;


@Controller
@RequestMapping("/company/*")
public class CompanyController {

    private final SideBarService sideBarService;

    @Autowired
    public CompanyController(SideBarService sideBarService) {
        this.sideBarService = sideBarService;
    }

    @GetMapping("/")
    public String companyMain(Principal principal, Model model) {
        String url = "redirect:/";
        if(principal!=null) {
            url = "company/main.html";
            sideBarService.findUserInfo(principal.getName(), model);
        }
        return url;
    }

}
