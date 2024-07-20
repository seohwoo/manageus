package com.project.manageus.controller.company;

import com.project.manageus.dto.UserInfoDTO;
import com.project.manageus.service.ProfileService;
import com.project.manageus.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/manageus/profile/*")
public class ProfileController {

    private final UrlService urlService;
    private final ProfileService profileService;

    @Autowired
    public ProfileController(UrlService urlService,
                             ProfileService profileService) {
        this.urlService = urlService;
        this.profileService = profileService;
    }

    @GetMapping("users/{id}")
    public String showProfile(@PathVariable Long id,
                              Principal principal,
                              Model model) {
        String url = "company/profile/profile.html";
        if(!urlService.isValidUser(principal.getName(), model)) {
            url = "redirect:manageus/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        profileService.showUserProfile(id, principal, model);
        return url;
    }

    @GetMapping("users/{id}/edit")
    public String updateProfileForm(@PathVariable Long id,
                                    Principal principal,
                                    Model model) {
        String url = "company/profile/profile-update-form.html";
        if(!urlService.isValidUser(principal.getName(), model)
                || id!=Long.parseLong(principal.getName())) {
            url = "redirect:/manageus/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        profileService.showUserProfile(id, principal, model);
        return url;
    }

    @PutMapping("users/{id}")
    public String updateProfile(@PathVariable Long id,
                                Principal principal,
                                UserInfoDTO userInfoDTO,
                                MultipartFile stampFile) {
        Long companyId = Long.parseLong(urlService.findCompanyUrl(principal.getName()));
        String url = "redirect:/manageus/companies/" + companyId;
        if(Objects.equals(id, userInfoDTO.getId())) {
            profileService.updateUser(userInfoDTO, stampFile);
            url = "redirect:/manageus/profile/users/" + id;
        }
        return url;
    }

}
