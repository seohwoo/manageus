package com.project.manageus.service;

import com.project.manageus.dto.*;
import com.project.manageus.entity.CompanyEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final CompanyRepository companyRepository;
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository,
                            UserInfoRepository userInfoRepository,
                            CompanyRepository companyRepository,
                            AuthRepository authRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder) {
       this.userRepository = userRepository;
       this.userInfoRepository = userInfoRepository;
       this.companyRepository = companyRepository;
       this.authRepository = authRepository;
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
            Long newId = companyEntity.getId() * 10000 + 1;
            if(userRepository.existsByCompanyId(companyEntity.getId())) {
                newId = Collections.max(userRepository.findAllByCompanyId(companyEntity.getId()), Comparator.comparingLong(UserEntity::getId)).getId() + 1;
            }
            userDTO.setId(newId);
            userInfoDTO.setId(newId);
            userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            userRepository.save(userDTO.toUserEntity());
            userInfoRepository.save(userInfoDTO.toUserInfoEntity());
            result = true;
        }

        return result;
    }


    // 유일한 방문 코드를 생성하는 메소드
    public String generateUniqueVisitCode() {
        String newCode = UUID.randomUUID().toString();
        while (companyRepository.existsByInviteCode(newCode)) {
            newCode = UUID.randomUUID().toString();
        }
        return newCode;
    }

    @Override
    public boolean createCompany(CompanyDTO companyDTO) {
        boolean result = false;
        if(!companyRepository.existsByEmail(companyDTO.getEmail())
                && !companyRepository.existsByBusinessNum(companyDTO.getBusinessNum())) {
            companyDTO.setStatusId((long) 1001);
            companyDTO.setAuthId((long) 2);
            companyDTO.setInviteCode(generateUniqueVisitCode());
            companyDTO.setPw(bCryptPasswordEncoder.encode(companyDTO.getPw()));
            companyRepository.save(companyDTO.toCompanyEntity());
            result = true;
        }
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long id = Long.parseLong(username);
        if(userRepository.existsById(id)) {
            if(id>999 && id<10000) {
                Optional<CompanyEntity> optionalCompany = companyRepository.findById(id);
                if (optionalCompany.isPresent()) {
                    return new JoinCompanyDTO(optionalCompany.get(), authRepository);
                }
            }else {
                Optional<UserEntity> optionalUser = userRepository.findById(id);
                if (optionalUser.isPresent()) {
                    return new JoinUserDTO(optionalUser.get(), authRepository);
                }
            }
        }
        return null;
    }
}
