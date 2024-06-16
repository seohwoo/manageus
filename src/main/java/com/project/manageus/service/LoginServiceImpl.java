package com.project.manageus.service;

import com.project.manageus.dto.UserDTO;
import com.project.manageus.dto.UserInfoDTO;
import com.project.manageus.entity.CompanyEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository,
                            UserInfoRepository userInfoRepository,
                            CompanyRepository companyRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder) {
       this.userRepository = userRepository;
       this.userInfoRepository = userInfoRepository;
       this.companyRepository = companyRepository;
       this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean createUser(UserDTO userDTO, UserInfoDTO userInfoDTO, String inviteCode) {
        boolean result = false;

        if(companyRepository.existsByInviteCode(inviteCode) &&
                !userInfoRepository.existsByEmail(userInfoDTO.getEmail())) {

            CompanyEntity companyEntity = companyRepository.findByInviteCode(inviteCode);
            userDTO.setCompanyId(companyEntity.getId());
            userDTO.setAuthId((long) 3);
            userDTO.setStatusId((long) 1001);
            Long newId = Collections.max(userRepository.findAll(), Comparator.comparingLong(UserEntity::getId)).getId() + 1;
            userDTO.setId(newId);
            userInfoDTO.setId(newId);
            userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            userRepository.save(userDTO.toUserEntity());
            userInfoRepository.save(userInfoDTO.toUserInfoEntity());
            result = true;
        }

        return result;
    }
}
