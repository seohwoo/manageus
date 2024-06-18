package com.project.manageus.service;

import com.project.manageus.dto.CompanyDTO;
import com.project.manageus.dto.UserDTO;
import com.project.manageus.dto.UserInfoDTO;

public interface LoginService {

    public String findUserCompanyId(String username);
    public boolean createUser(UserDTO userDTO, UserInfoDTO userInfoDTO, String inviteCode);
    public boolean createCompany(CompanyDTO companyDTO);
}
