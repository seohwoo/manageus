package com.project.manageus.service;

import org.springframework.ui.Model;

public interface UrlService {

    public boolean findUserInfo(String username, Long compnayId, Model model);
    public String findCompanyUrl(String username);

}
