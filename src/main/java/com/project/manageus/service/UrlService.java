package com.project.manageus.service;

import org.springframework.ui.Model;

public interface UrlService {

    public boolean findUserInfo(String username, Long companyId, Model model);
    public boolean findCompanyInfo(String username, Long companyId, Model model);
    public boolean isValidUser(String username, Model model);
    public boolean isValidCompany(String username, Model model);
    public String findCompanyUrl(String username);


}
