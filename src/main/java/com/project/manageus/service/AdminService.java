package com.project.manageus.service;

import com.project.manageus.dto.CompanyDTO;
import com.project.manageus.dto.DepartmentDTO;
import com.project.manageus.dto.UserDTO;
import org.springframework.ui.Model;

public interface AdminService {


    public void findAllEmployee(Long companyId, Long statusId, Model model);
    public Long findStatusIdById(Long id);
    public boolean updateUser(UserDTO userDTO);
    public void findAllDepartment(Long companyId, Model model);
    public boolean createDepartment(DepartmentDTO departmentDTO);
    public boolean updateDepartment(DepartmentDTO departmentDTO);
    public void findDepartmentById(Long departmentId, Model model);
    public void findCompanyInfo(Long companyId, Model model);
    public boolean updateCompanyInfo(CompanyDTO companyDTO);
}
