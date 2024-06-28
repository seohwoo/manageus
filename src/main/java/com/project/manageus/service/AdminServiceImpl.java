package com.project.manageus.service;

import com.project.manageus.dto.UserDTO;
import com.project.manageus.entity.StatusEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.repository.StatusRepository;
import com.project.manageus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository,
                            StatusRepository statusRepository) {
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public boolean findAllEmployee(Long companyId, Model model) {
        boolean result = false;
        List<UserEntity> userEntityList = userRepository.findAllByCompanyIdAndStatusId(companyId, (long) 1002);
        if(userEntityList != null) {
            model.addAttribute("userEntityList", userEntityList);
            result = true;
        }
        return result;
    }

    @Override
    public boolean findAllPendingEmployee(Long companyId, Model model) {
        boolean result = false;
        List<UserEntity> userEntityList = userRepository.findAllByCompanyIdAndStatusId(companyId, (long) 1001);
        if(userEntityList != null) {
            model.addAttribute("userEntityList", userEntityList);
            List<StatusEntity> statusEntityList = statusRepository.findByIdBetween((long) 1002, (long) 1003);
            model.addAttribute("statusEntityList", statusEntityList);
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateUserStatus(Long userId, Long statusId) {
        boolean result = false;
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            UserDTO userDTO = optionalUser.get().toUserDTO();
            userDTO.setStatusId(statusId);
            userRepository.save(userDTO.toUserEntity());
            result = true;
        }
        return result;
    }
}
