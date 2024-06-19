package com.project.manageus.controller.company;

import com.project.manageus.dto.ProjectDTO;
import com.project.manageus.service.ProjectService;
import com.project.manageus.service.ProjectServiceImpl;
import com.project.manageus.service.UrlService;
import com.project.manageus.service.UrlServiceImpl;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/company/{companyId}/*")
public class ProjectController {

    private final UrlService urlService;
    private final ProjectService projectService;

    @Autowired
    public ProjectController(UrlService urlService, ProjectService projectService) {
        this.urlService = urlService;
        this.projectService = projectService;
    }

    @GetMapping("/project")
    public String main(@PathVariable Long companyId, Principal principal, Model model) {
        String url = "/company/project/main.html";
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/company/" + urlService.findCompanyUrl(principal.getName());
        }

        Long id = Long.parseLong(principal.getName());
        List<ProjectDTO> myProjectList = projectService.projectList(id);
        model.addAttribute("myProjectList",myProjectList);




        return url;
    }

    @GetMapping("/project/detail")
    public String detail() {

        return "/project/main.html";
    }
}
