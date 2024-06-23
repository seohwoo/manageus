package com.project.manageus.service;

import com.project.manageus.entity.DepartmentEntity;
import com.project.manageus.entity.PositionEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.entity.UserInfoEntity;
import com.project.manageus.repository.DepartmentRepository;
import com.project.manageus.repository.PositionRepository;
import com.project.manageus.repository.UserInfoRepository;
import com.project.manageus.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;

    public ProfileServiceImpl(UserRepository userRepository,
                              UserInfoRepository userInfoRepository,
                              PositionRepository positionRepository,
                              DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository =  userInfoRepository;
        this.positionRepository = positionRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void showUserProfile(Long id, Principal principal, Model model) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            model.addAttribute("isLoginUser", id == Long.parseLong(principal.getName()));
            model.addAttribute("userId", optionalUser.get().getUserInfo().getId());
            model.addAttribute("username", optionalUser.get().getUserInfo().getName());
            model.addAttribute("email", optionalUser.get().getUserInfo().getEmail());
            model.addAttribute("phone", optionalUser.get().getUserInfo().getPhone());
            model.addAttribute("address", optionalUser.get().getUserInfo().getAddress());
            model.addAttribute("birth", optionalUser.get().getUserInfo().getBirth());
            model.addAttribute("gender", optionalUser.get().getUserInfo().getGender());
            model.addAttribute("stamp", optionalUser.get().getUserInfo().getStamp() == null ? "직인없음" : optionalUser.get().getUserInfo().getStamp());
            model.addAttribute("position", optionalUser.get().getPosition().getName());
            model.addAttribute("department", optionalUser.get().getDepartment().getName());
        }
    }
}
