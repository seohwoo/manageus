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
            Optional<UserInfoEntity> optionalUserInfo = userInfoRepository.findById(id);
            if(optionalUserInfo.isPresent()) {
                model.addAttribute("userId", optionalUserInfo.get().getId());
                model.addAttribute("username", optionalUserInfo.get().getName());
                model.addAttribute("email", optionalUserInfo.get().getEmail());
                model.addAttribute("phone", optionalUserInfo.get().getPhone());
                model.addAttribute("address", optionalUserInfo.get().getAddress());
                model.addAttribute("birth", optionalUserInfo.get().getBirth());
                model.addAttribute("gender", optionalUserInfo.get().getGender());
                model.addAttribute("stamp", optionalUserInfo.get().getStamp() == null ? "직인없음" : optionalUserInfo.get().getStamp());
            }
            Optional<PositionEntity> optionalPosition = positionRepository.findById(optionalUser.get().getPositionId());
            if(optionalPosition.isPresent()) {
                model.addAttribute("position", optionalPosition.get().getName());
            }
            Optional<DepartmentEntity> optionalDepartment = departmentRepository.findById(optionalUser.get().getDepartmentId());
            if(optionalDepartment.isPresent()) {
                model.addAttribute("department", optionalDepartment.get().getName());
            }
        }
    }
}
