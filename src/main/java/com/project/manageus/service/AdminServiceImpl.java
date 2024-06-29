package com.project.manageus.service;

import com.project.manageus.dto.UserDTO;
import com.project.manageus.entity.DepartmentEntity;
import com.project.manageus.entity.PositionEntity;
import com.project.manageus.entity.StatusEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.repository.DepartmentRepository;
import com.project.manageus.repository.PositionRepository;
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
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository,
                            StatusRepository statusRepository,
                            PositionRepository positionRepository,
                            DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.positionRepository = positionRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public boolean findAllEmployee(Long companyId, Model model) {
        boolean result = false;
        List<UserEntity> userEntityList = userRepository.findAllByCompanyIdAndStatusId(companyId, (long) 1002);
        if(userEntityList != null) {
            model.addAttribute("userEntityList", userEntityList);
            List<PositionEntity> positionEntityList = positionRepository.findAll();
            model.addAttribute("positionEntityList", positionEntityList);
            List<DepartmentEntity> departmentEntityList = departmentRepository.findAllByCompanyId(companyId);
            model.addAttribute("departmentEntityList", departmentEntityList);
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
    public boolean updateUserInfo(Long userId, Long positionId, Long departmentId) {
        boolean result = false;
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            UserDTO userDTO = optionalUser.get().toUserDTO();
            userDTO.setPositionId(positionId);
            userDTO.setDepartmentId(departmentId);
            userRepository.save(userDTO.toUserEntity());
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
