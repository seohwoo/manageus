package com.project.manageus.service;

import com.project.manageus.entity.CompanyEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.entity.UserInfoEntity;
import com.project.manageus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService{

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public UrlServiceImpl(UserRepository userRepository,
                          CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean findUserInfo(String username,Long companyId, Model model) {
        boolean result = false;
        Long id = Long.parseLong(username);
        model.addAttribute("id", id);
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            Optional<CompanyEntity> optionalCompany = companyRepository.findById(optionalUser.get().getCompanyId());
            if(optionalCompany.isPresent()) {
                if(Objects.equals(optionalUser.get().getCompanyId(), companyId)) {
                    result = true;
                }
                model.addAttribute("company", optionalCompany.get().getName());
            }
            model.addAttribute("name", optionalUser.get().getUserInfo().getName());
            if(optionalUser.get().getUserInfo().getGender().equals("남자")) {
                model.addAttribute("profileImage", "/img/undraw_profile_2.svg");
            }else {
                model.addAttribute("profileImage", "/img/undraw_profile_3.svg");
            }
        }
        return result;
    }

    @Override
    public boolean findCompanyInfo(String username, Long companyId, Model model) {
        boolean result = false;
        if(Long.parseLong(username) == companyId) {
            Optional<CompanyEntity> optionalCompany = companyRepository.findById(companyId);
            if(optionalCompany.isPresent()) {
                model.addAttribute("companyId", companyId);
                model.addAttribute("company", optionalCompany.get().getName());
                model.addAttribute("ceo", optionalCompany.get().getCeo());
            }
            result = true;
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
