package com.project.manageus.service;

import org.springframework.ui.Model;

public interface UrlService {

    public boolean findUserInfo(String username, Long companyId, Model model);
    public boolean findCompanyInfo(String username, Long companyId, Model model);
    public String findCompanyUrl(String username);


}
