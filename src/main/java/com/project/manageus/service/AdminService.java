package com.project.manageus.service;

import org.springframework.ui.Model;

public interface AdminService {


    public boolean findAllEmployee(Long companyId, Model model);
    public boolean findAllPendingEmployee(Long companyId, Model model);
    public boolean updateUserStatus(Long userId, Long statusId);
}
