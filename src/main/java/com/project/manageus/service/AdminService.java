package com.project.manageus.service;

import org.springframework.ui.Model;

public interface AdminService {


    public boolean findAllEmployee(Long companyId, Model model);
    public boolean findAllPendingEmployee(Long companyId, Model model);
    public boolean updateUserInfo(Long userId, Long positionId, Long departmentId, Long statusId);
    public boolean updateUserStatus(Long userId, Long statusId);

}
