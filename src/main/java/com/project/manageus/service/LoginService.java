package com.project.manageus.service;

import com.project.manageus.dto.MemberDTO;
import com.project.manageus.dto.MemberInfoDTO;

public interface LoginService {

    boolean createMember(MemberDTO memberDTO, MemberInfoDTO memberInfoDTO, String inviteCode);

}
