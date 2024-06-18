package com.project.manageus.controller.all;

import com.project.manageus.dto.CompanyDTO;
import com.project.manageus.dto.UserDTO;
import com.project.manageus.dto.UserInfoDTO;
import com.project.manageus.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login() {
        String url = "/all/login/login.html";
        return url;
    }

    @GetMapping("/register")
    public String register() {
        String url = "/all/login/register.html";
        return url;
    }

    @PostMapping("/register")
    public String registerPro(UserDTO userDTO, UserInfoDTO userInfoDTO, String repeatPassword, String inviteCode) {
        String url = "redirect:/register";
        if(userDTO.getPassword().equals(repeatPassword)) {
            boolean isUserCreated = loginService.createUser(userDTO, userInfoDTO, inviteCode);
            if(isUserCreated) {
                url = "redirect:/login";
            }
        }
        return url;
    }

    @GetMapping("/company")
    public String company() {
        String url = "/all/login/company-register.html";
        return url;
    }

    @PostMapping("/company")
    public String companyPro(CompanyDTO companyDTO, String repeatPassword) {
        String url = "redirect:/company";
        if(companyDTO.getPw().equals(repeatPassword)) {
            boolean isCompanyCreated = loginService.createCompany(companyDTO);
            if(isCompanyCreated) {
                url = "redirect:/login";
            }
        }
        return url;
    }

    @GetMapping("/forgot")
    public String forgot() {
        String url = "/all/login/forgot.html";
        return url;
    }

}
