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
@RequestMapping("/companies/{companyId}/profiles/*")
public class ProfileController {

    private final UrlService urlService;
    private final ProfileService profileService;

    @Autowired
    public ProfileController(UrlService urlService,
                             ProfileService profileService) {
        this.urlService = urlService;
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public String showProfile(@PathVariable Long companyId,
                              @PathVariable Long id,
                              Principal principal,
                              Model model) {
        String url = "company/profile/profile.html";
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        profileService.showUserProfile(id, principal, model);
        return url;
    }

    @GetMapping("/{id}/form")
    public String updateProfileForm(@PathVariable Long companyId,
                                    @PathVariable Long id,
                                    Principal principal,
                                    Model model) {
        String url = "company/profile/profile-update-form.html";
        if(!urlService.findUserInfo(principal.getName(), companyId, model)
                || id!=Long.parseLong(principal.getName())) {
            url = "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        profileService.showUserProfile(id, principal, model);
        return url;
    }

    @PutMapping("/{id}")
    public String updateProfile(@PathVariable Long companyId, @PathVariable Long id,
                                UserInfoDTO userInfoDTO,
                                MultipartFile stampFile) {
        String url = "redirect:/companies/" + companyId;
        if(Objects.equals(id, userInfoDTO.getId())) {
            profileService.updateUser(userInfoDTO, stampFile);
            url = "redirect:/companies/" + companyId + "/profiles/" + id;
        }
        return url;
    }



}
