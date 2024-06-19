package com.project.manageus.service;

import org.springframework.ui.Model;

public interface SideBarService {

    public void findUserInfo(String username, Model model);
}
