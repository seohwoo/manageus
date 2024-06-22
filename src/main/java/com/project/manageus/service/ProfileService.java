package com.project.manageus.service;

import org.springframework.ui.Model;

import java.security.Principal;

public interface ProfileService {

    public void showUserProfile(Long id, Principal principal, Model model);

}
