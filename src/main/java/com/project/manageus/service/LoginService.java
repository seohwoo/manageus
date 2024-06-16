package com.project.manageus.service;

import com.project.manageus.dto.UserDTO;
import com.project.manageus.dto.UserInfoDTO;

public interface LoginService {

    boolean createMember(UserDTO userDTO, UserInfoDTO userInfoDTO, String inviteCode);

}
