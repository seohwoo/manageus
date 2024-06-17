package com.project.manageus.service;

import com.project.manageus.dto.CompanyDTO;
import com.project.manageus.dto.UserDTO;
import com.project.manageus.dto.UserInfoDTO;

public interface LoginService {

    boolean createUser(UserDTO userDTO, UserInfoDTO userInfoDTO, String inviteCode);
    boolean createCompany(CompanyDTO companyDTO);

}
