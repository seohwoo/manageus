package com.project.manageus.controller.company;


import com.project.manageus.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/companies/*")
public class CompanyController {

    private final UrlService urlService;

    @Autowired
    public CompanyController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{companyId}")
    public String companyMain(@PathVariable Long companyId, Principal principal, Model model) {
        String url = "company/main.html";
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        return url;
    }

}
