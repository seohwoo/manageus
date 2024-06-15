package com.project.manageus.service;

import com.project.manageus.dto.MemberDTO;
import com.project.manageus.dto.MemberInfoDTO;
import com.project.manageus.entity.CompanyEntity;
import com.project.manageus.entity.MemberEntity;
import com.project.manageus.entity.MemberInfoEntity;
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
    public boolean createMember(MemberDTO memberDTO, MemberInfoDTO memberInfoDTO, String inviteCode) {
        boolean result = false;
        int isUser = memberInfoRepository.countByEmail(memberInfoDTO.getEmail());

        if(isUser == 0) {
            System.out.println(inviteCode);
            CompanyEntity companyEntity = companyRepository.findByInviteCode(inviteCode);
            MemberEntity memberEntity = null;
            MemberInfoEntity memberInfoEntity = memberInfoDTO.toMemberInfoEntity();
            System.out.println("companyEntity == null");
            if(companyEntity != null) {
                System.out.println("companyEntity != null");
                if (companyEntity.getInviteCode().equals(inviteCode)) {
                    System.out.println("companyEntity.getInviteCode().equals(inviteCode)");
                    memberDTO.setCompanyId(companyEntity.getId());
                    memberDTO.setPw(bCryptPasswordEncoder.encode(memberDTO.getPw()));
                    memberEntity = memberDTO.toMemberEntity();
                    memberRepository.save(memberEntity);
                    memberInfoRepository.save(memberInfoEntity);
                    System.out.println("create");
                    result = true;
                }
            }
        }
        return result;
    }
}
