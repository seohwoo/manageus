package com.project.manageus.controller.company;

import com.project.manageus.dto.ProjectDTO;
import com.project.manageus.dto.ProjectDetailDTO;
import com.project.manageus.entity.ProjectDetailEntity;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/companies/{companyId}/users/*")
public class ProjectController {

    private final UrlService urlService;
    private final ProjectService projectService;

    @Autowired
    public ProjectController(UrlService urlService, ProjectService projectService) {
        this.urlService = urlService;
        this.projectService = projectService;
    }

    @GetMapping("/{userId}")
    public String projectMain(@PathVariable Long companyId, @PathVariable Long userId,Principal principal, Model model) {
        String url = "/company/project/projectMain.html";
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/company/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        List<ProjectDTO> myProjectList = projectService.projectList(userId);
        model.addAttribute("myProjectList",myProjectList);




        return url;
    }

    @GetMapping("/{userId}/project/{projectId}")
    public String projectDetail(@PathVariable Long userId, @PathVariable Long companyId, @PathVariable Long projectId, Principal principal, Model model) {

        String url = "/company/project/projectDetail.html";
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/company/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        Long leaderId = projectService.projectLeaderId(projectId);
        int projectMemberCount = projectService.projectMemberCount(projectId);
        List<ProjectDetailEntity> projectDetailList = projectService.projectDetailList(projectId);

        model.addAttribute("projectMemberCount",projectMemberCount);
        model.addAttribute("leaderId",leaderId);
        model.addAttribute("userId",userId);
        model.addAttribute("projectId",projectId);
        model.addAttribute("companyId",companyId);
        model.addAttribute("projectDetailList",projectDetailList);

        return url;
    }

    @GetMapping("/{userId}/project/{projectId}/addContent")
    public String addContent(@PathVariable Long userId, @PathVariable Long companyId, @PathVariable Long projectId, Principal principal, Model model){
        String url = "/company/project/addContent.html";
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/company/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        model.addAttribute("projectId",projectId);
        return url;
    }

    @PostMapping("/{userId}/project/{projectId}/addContentPro")
    public String addContentPro(ProjectDetailDTO projectDetailDTO, @PathVariable Long userId, @PathVariable Long companyId, @PathVariable Long projectId, Principal principal, Model model){
        String url = "/company/project/addContentPro.html";
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/company/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        int result = projectService.insertProjectDetail(projectDetailDTO);
        System.out.println("레셜트=========================="+result);
        model.addAttribute("result",result);
        model.addAttribute("userId",userId);
        model.addAttribute("companyId",companyId);
        model.addAttribute("projectId",projectId);
        return url;
    }
}
