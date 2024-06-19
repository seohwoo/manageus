package com.project.manageus.controller.company;

import com.project.manageus.service.UrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/company/{companyId}/profile/*")
public class ProfileController {

    private final UrlServiceImpl urlService;

    @Autowired
    public ProfileController(UrlServiceImpl urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{id}")
    public String showProfile(@PathVariable Long companyId,
                              @PathVariable Long id,
                              Principal principal,
                              Model model) {
        String url = "redirect:/";
        url = "company/profile/profile.html";
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/company/" + urlService.findCompanyUrl(principal.getName());
        }




        return url;
    }



}
