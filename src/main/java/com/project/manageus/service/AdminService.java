package com.project.manageus.service;

import com.project.manageus.dto.CompanyDTO;
import com.project.manageus.dto.DepartmentDTO;
import org.springframework.ui.Model;

public interface AdminService {


    public boolean findAllEmployee(Long companyId, Model model);
    public boolean findAllPendingEmployee(Long companyId, Model model);
    public boolean findAllExitEmployee(Long companyId, Model model);
    public boolean updateUserInfo(Long userId, Long positionId, Long departmentId, Long statusId);
    public boolean updateUserStatus(Long userId, Long statusId);
    public void findAllDepartment(Long companyId, Model model);
    public boolean createDepartment(DepartmentDTO departmentDTO);
    public void findCompanyInfo(Long companyId, Model model);
    public boolean updateCompanyInfo(CompanyDTO companyDTO);
}
