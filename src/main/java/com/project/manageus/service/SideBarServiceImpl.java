package com.project.manageus.service;

import com.project.manageus.entity.CompanyEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.entity.UserInfoEntity;
import com.project.manageus.repository.CompanyRepository;
import com.project.manageus.repository.UserInfoRepository;
import com.project.manageus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class SideBarServiceImpl implements SideBarService{

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public SideBarServiceImpl(UserRepository userRepository,
                              UserInfoRepository userInfoRepository,
                              CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void findUserInfo(String username, Model model) {
        Long id = Long.parseLong(username);
        model.addAttribute("id", id);
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            Optional<CompanyEntity> optionalCompany = companyRepository.findById(optionalUser.get().getCompanyId());
            if(optionalCompany.isPresent()) {
                model.addAttribute("company", optionalCompany.get().getName());
            }
        }

        Optional<UserInfoEntity> optionalUserInfo = userInfoRepository.findById(id);
        if(optionalUserInfo.isPresent()) {
            model.addAttribute("name", optionalUserInfo.get().getName());
            if(optionalUserInfo.get().getGender().equals("남자")) {
                model.addAttribute("profileImage", "/img/undraw_profile_2.svg");
            }else {
                model.addAttribute("profileImage", "/img/undraw_profile_3.svg");
            }
        }
    }
}
