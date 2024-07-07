package com.project.manageus.service;

import com.project.manageus.dto.UserInfoDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface ProfileService {

    public void showUserProfile(Long id, Principal principal, Model model);
    public void updateUser(UserInfoDTO userInfoDTO, MultipartFile multipartFile);

}
