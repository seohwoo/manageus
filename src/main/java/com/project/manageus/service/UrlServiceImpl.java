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

import java.util.Objects;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService{

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public UrlServiceImpl(UserRepository userRepository,
                              UserInfoRepository userInfoRepository,
                              CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean findUserInfo(String username,Long compnayId, Model model) {
        boolean result = false;
        Long id = Long.parseLong(username);
        model.addAttribute("id", id);
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            Optional<CompanyEntity> optionalCompany = companyRepository.findById(optionalUser.get().getCompanyId());
            if(optionalCompany.isPresent()) {
                if(Objects.equals(optionalUser.get().getCompanyId(), compnayId)) {
                    System.out.println(Objects.equals(optionalUser.get().getCompanyId(), compnayId));
                    result = true;
                }
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
        return result;
    }

    @Override
    public String findCompanyUrl(String username) {
        String companyUrl = "";
        Long id = Long.parseLong(username);
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            companyUrl = optionalUser.get().getCompanyId().toString();
        }
        return companyUrl;
    }


}
