package com.project.manageus.service.impl;

import com.project.manageus.bean.UserDTO;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.repository.UserRepository;
import com.project.manageus.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void joinProcess(UserDTO dto) {

    }
}
