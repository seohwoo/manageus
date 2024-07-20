package com.project.manageus.service;

import com.project.manageus.dto.CompanyDTO;
import com.project.manageus.dto.DepartmentDTO;
import com.project.manageus.dto.UserDTO;
import com.project.manageus.entity.*;
import com.project.manageus.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    private final ApprovalJPARepository approvalJPARepository;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceCRUDRepository attendanceCRUDRepository;


    @Override
    public void findAllEmployee(Long companyId,Long statusId, Model model) {
        List<UserEntity> userEntityList = userRepository.findAllByCompanyIdAndStatusId(companyId, statusId);
        if(!userEntityList.equals(Collections.emptyList())) {
            model.addAttribute("userEntityList", userEntityList);
            List<PositionEntity> positionEntityList = positionRepository.findAll();
            model.addAttribute("positionEntityList", positionEntityList);
            List<DepartmentEntity> departmentEntityList = departmentRepository.findAllByCompanyId(companyId);
            model.addAttribute("departmentEntityList", departmentEntityList);
            List<StatusEntity> statusEntityList = statusRepository.findByIdBetween((long) 1002, (long) 1003);
            model.addAttribute("statusEntityList", statusEntityList);
        }
    }



    @Override
    public void findAllDepartment(Long companyId, Model model) {
        List<DepartmentDTO> departmentDTOList = new ArrayList<>(Collections.emptyList());
        List<DepartmentEntity> departmentEntityList = departmentRepository.findAllByCompanyId(companyId);
        for (DepartmentEntity departmentEntity : departmentEntityList) {
            DepartmentDTO departmentDTO = departmentEntity.toDepartmentDTO();
            departmentDTO.setDepartmentUserCnt(userRepository.countByDepartmentId(departmentEntity.getId()));
            departmentDTOList.add(departmentDTO);
        }
        model.addAttribute("departmentDTOList", departmentDTOList);
    }

    @Override
    public Long findStatusIdById(Long id) {
        Long statusId = 1002L;
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            statusId = optionalUser.get().getStatusId();
        }
        return statusId;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        boolean result = false;
        Optional<UserEntity> optionalUser = userRepository.findById(userDTO.getId());
        if(optionalUser.isPresent()) {
            UserDTO newUserDTO = optionalUser.get().toUserDTO();
            newUserDTO.setStatusId(userDTO.getStatusId());
            if(userDTO.getDepartmentId() != null
                    && userDTO.getPositionId() != null) {
                newUserDTO.setDepartmentId(userDTO.getDepartmentId());
                newUserDTO.setPositionId(userDTO.getPositionId());
            }
            userRepository.save(newUserDTO.toUserEntity());
            result = true;
        }
        return result;
    }

    @Override
    public boolean createDepartment(DepartmentDTO departmentDTO) {
        boolean result = false;
        if(!departmentRepository.existsByNameAndCompanyId(departmentDTO.getName(), departmentDTO.getCompanyId())) {
            Long newId = departmentDTO.getCompanyId() * 100 + 1;
            if(departmentRepository.existsByCompanyId(departmentDTO.getCompanyId())) {
                newId = Collections.max(departmentRepository.findAllByCompanyId(departmentDTO.getCompanyId()), Comparator.comparingLong(DepartmentEntity::getId)).getId() + 1;
            }
            departmentDTO.setId(newId);
            departmentRepository.save(departmentDTO.toDepartmentEntity());
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateDepartment(DepartmentDTO departmentDTO) {
        boolean result = false;
        if(!departmentRepository.existsByNameAndCompanyId(departmentDTO.getName(), departmentDTO.getCompanyId())) {
            Optional<DepartmentEntity> optionalDepartment = departmentRepository.findById(departmentDTO.getId());
            if(optionalDepartment.isPresent()) {
                DepartmentDTO newDepartmentDTO = optionalDepartment.get().toDepartmentDTO();
                newDepartmentDTO.setName(departmentDTO.getName());
                departmentRepository.save(newDepartmentDTO.toDepartmentEntity());
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean deleteDepartment(Long departmentId) {
        boolean result = false;
        Optional<DepartmentEntity> optionalDepartment = departmentRepository.findById(departmentId);
        if(optionalDepartment.isPresent()) {
            if(userRepository.countByDepartmentId(departmentId) == 0) {
                departmentRepository.delete(optionalDepartment.get());
                result = true;
            }
        }
        return result;
    }

    @Override
    public void findDepartmentById(Long departmentId, Model model) {
        Optional<DepartmentEntity> optionalDepartment = departmentRepository.findById(departmentId);
        if(optionalDepartment.isPresent()) {
            model.addAttribute("departmentId", optionalDepartment.get().getId());
            model.addAttribute("departmentName", optionalDepartment.get().getName());
        }
    }

    @Override
    public void findCompanyInfo(Long companyId, Model model) {
        Optional<CompanyEntity> optionalCompany =  companyRepository.findById(companyId);
        if(optionalCompany.isPresent()) {
            model.addAttribute("companyName", optionalCompany.get().getName());
            model.addAttribute("businessNum", optionalCompany.get().getBusinessNum());
            model.addAttribute("inviteCode", optionalCompany.get().getInviteCode());
            model.addAttribute("ceo", optionalCompany.get().getCeo());
            model.addAttribute("employees", companyRepository.count());
            model.addAttribute("address", optionalCompany.get().getAddress());
            model.addAttribute("email", optionalCompany.get().getEmail());
            model.addAttribute("regDate", optionalCompany.get().getRegDate());
        }
    }

    @Override
    public boolean updateCompanyInfo(CompanyDTO companyDTO) {
        boolean result = false;
        Optional<CompanyEntity> optionalCompany = companyRepository.findById(companyDTO.getId());
        if(optionalCompany.isPresent()) {
            companyDTO.setPw(optionalCompany.get().getPw());
            companyDTO.setStatusId(optionalCompany.get().getStatusId());
            companyDTO.setAuthId(optionalCompany.get().getAuthId());
            companyDTO.setRegDate(optionalCompany.get().getRegDate());
            companyRepository.save(companyDTO.toCompanyEntity());
            result = true;
        }
        return result;
    }

    @Override
    public void findAllApproval(Long companyId, Model model) {
        List<ApprovalEntity> approvalEntities = approvalJPARepository.findByCompanyIdOrderBySignOnDesc(companyId);
        model.addAttribute("approvalEntities", approvalEntities);
    }

    public void findAllAttendance(Long companyId, Model model) {
        Optional<CompanyEntity> optionalCompany = companyRepository.findById(companyId);
        if (optionalCompany.isPresent()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            String startTime = sdf.format(currentDate);

            model.addAttribute("currentDate", currentDate);
            model.addAttribute("allCnt", companyRepository.count());
            model.addAttribute("startCnt", attendanceCRUDRepository.countByStartTimeStartingWith(startTime));

            List<UserEntity> userEntityList = userRepository.findAllByCompanyId(companyId);
            List<UserDTO> userDTOList = new ArrayList<>();
            for (UserEntity userEntity : userEntityList) {
                UserDTO userDTO = userEntity.toUserDTO();
                userDTO.setName(userEntity.getUserInfo().getName());
                userDTO.setPositionName(userEntity.getPosition()==null ? "직급미정" : userEntity.getPosition().getName());
                userDTO.setDepartmentName(userEntity.getDepartment()==null ? "부서미정" : userEntity.getDepartment().getName());
                if(attendanceCRUDRepository.countByUserIdAndStartTimeStartingWith(userDTO.getId(), startTime) == 1) {
                    Optional<AttendanceEntity> optionalAttendance = attendanceCRUDRepository.findByUserIdAndStartTimeStartingWith(userDTO.getId(), startTime);
                    if(optionalAttendance.isPresent()) {
                        userDTO.setStartDate(optionalAttendance.get().getStartTime());
                        userDTO.setEndDate(optionalAttendance.get().getEndTime());
                        userDTO.setNote(optionalAttendance.get().getNote());
                    }
                }
                userDTOList.add(userDTO);
            }
            model.addAttribute("userDTOList", userDTOList);
        }
    }

}
