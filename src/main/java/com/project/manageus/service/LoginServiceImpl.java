package com.project.manageus.service;

import com.project.manageus.dto.UserDTO;
import com.project.manageus.dto.UserInfoDTO;
import com.project.manageus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginServiceImpl(MemberRepository memberRepository,
                            MemberInfoRepository memberInfoRepository,
                            CompanyRepository companyRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder) {
       this.memberRepository = memberRepository;
       this.memberInfoRepository = memberInfoRepository;
       this.companyRepository = companyRepository;
       this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean createMember(UserDTO userDTO, UserInfoDTO userInfoDTO, String inviteCode) {



        return false;
    }
}
