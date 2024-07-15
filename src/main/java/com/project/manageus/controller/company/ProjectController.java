package com.project.manageus.controller.company;

import com.project.manageus.dto.ProjectDTO;
import com.project.manageus.dto.ProjectDetailDTO;
import com.project.manageus.dto.ProjectMemberDTO;
import com.project.manageus.entity.ProjectDetailEntity;
import com.project.manageus.entity.ProjectEntity;
import com.project.manageus.service.ProjectService;
import com.project.manageus.service.ProjectServiceImpl;
import com.project.manageus.service.UrlService;
import com.project.manageus.service.UrlServiceImpl;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/manageus/project/*")
public class ProjectController {

    private final UrlService urlService;
    private final ProjectService projectService;

    @Autowired
    public ProjectController(UrlService urlService, ProjectService projectService) {
        this.urlService = urlService;
        this.projectService = projectService;
    }

    @GetMapping("/users/{userId}")
    public String projectMain( @PathVariable Long userId,Principal principal, Model model) {
        String url = "company/project/projectMain.html";
        if(!urlService.isValidUser(principal.getName(), model)) {
            url = "redirect:/manageus/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        List<ProjectDTO> myProjectList = projectService.projectList(userId);
        model.addAttribute("myProjectList",myProjectList);




        return url;
    }

    @GetMapping("/users/{userId}/projects/{projectId}")
    public String projectContent(@PathVariable Long userId, @PathVariable Long projectId, Principal principal, Model model) {

        String url = "company/project/projectContent.html";
        if(!urlService.isValidUser(principal.getName(), model)) {
            url = "redirect:/manageus/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        Long companyId = Long.valueOf(urlService.findCompanyUrl(principal.getName()));
        Long leaderId = projectService.projectLeaderId(projectId);
        int projectMemberCount = projectService.projectMemberCount(projectId);
        List<ProjectDetailEntity> projectDetailList = projectService.projectDetailList(projectId);

        int projectDetailCount = projectService.projectDetailCount(projectId);
        Long completeStatusId = 3002L;
        int projectDetailCompleteCount = projectService.projectDetailCompleteCount(projectId,completeStatusId);
        int taskValue = (int) Math.round((double) projectDetailCompleteCount / projectDetailCount * 100);
        model.addAttribute("projectMemberCount",projectMemberCount);
        model.addAttribute("leaderId",leaderId);
        model.addAttribute("userId",userId);
        model.addAttribute("projectId",projectId);
        model.addAttribute("companyId",companyId);
        model.addAttribute("projectDetailList",projectDetailList);
        model.addAttribute("taskValue",taskValue);

        return url;
    }

    @GetMapping("/users/{userId}/projects/{projectId}/details/new")
    public String addContent(@PathVariable Long userId, @PathVariable Long projectId, Principal principal, Model model){
        String url = "company/project/addContent.html";
        if(!urlService.isValidUser(principal.getName(), model)) {
            url = "redirect:/manageus/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        model.addAttribute("projectId",projectId);
        return url;
    }

    @PostMapping("/users/{userId}/projects/{projectId}/details")
    public String addContentPro(ProjectDetailDTO projectDetailDTO, @PathVariable Long userId, @PathVariable Long projectId, Principal principal, Model model){
        String url = "company/project/addContentPro.html";
        if(!urlService.isValidUser(principal.getName(),model)) {
            url = "redirect:/manageus/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        Long companyId = Long.valueOf(urlService.findCompanyUrl(principal.getName()));
        int result = projectService.insertProjectDetail(projectDetailDTO);
        model.addAttribute("result",result);
        model.addAttribute("userId",userId);
        model.addAttribute("companyId",companyId);
        model.addAttribute("projectId",projectId);
        return url;
    }

    @GetMapping("/users/{userId}/projects/new")
    public String addProject(@PathVariable Long userId, Principal principal, Model model){
        String url = "company/project/addProject.html";
        Long companyId = Long.valueOf(urlService.findCompanyUrl(principal.getName()));
        if(!urlService.isValidUser(principal.getName(),model)) {
            url = "redirect:/manageus/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        model.addAttribute("companyId",companyId);
        return url;
    }

    @PostMapping("/users/{userId}/projects")
    public String addProjectPro(ProjectDTO projectDTO, @RequestParam(required = false)Long[]plusUserId, @PathVariable Long userId, Principal principal, Model model){
        String url = "company/project/addProjectPro.html";
        Long companyId = Long.valueOf(urlService.findCompanyUrl(principal.getName()));
        projectDTO.setUserId(userId);
        projectDTO.setCompanyId(companyId);
        if(!urlService.isValidUser(principal.getName(),model)) {
            url = "redirect:/manageus/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }

        // 프로젝트등록, 해당아이디값 찾아오기
        ProjectEntity projectEntity = projectService.insertProject(projectDTO);
        Long projectId = projectEntity.getId();

        ProjectMemberDTO projectMemberDTO = new ProjectMemberDTO();
        projectMemberDTO.setProjectId(projectId);
        projectMemberDTO.setUserId(userId);
        projectService.insertProjectMember(projectMemberDTO);
        if(plusUserId != null) {
            for (Long member : plusUserId) {
                if (member != null) {
                    projectMemberDTO.setUserId(member);
                    projectService.insertProjectMember(projectMemberDTO);
                }
            }
        }

        return url;
    }
    @GetMapping("/users/{userId}/projects/{projectId}/details/{projectDetailId}")
    public String projectDetail(@PathVariable Long userId, @PathVariable Long projectId, @PathVariable Long projectDetailId,Principal principal, Model model) {
        Long companyId = Long.valueOf(urlService.findCompanyUrl(principal.getName()));
        String url = "company/project/projectDetail.html";
        if(!urlService.isValidUser(principal.getName(),model)) {
            url = "redirect:/manageus/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }

        ProjectDetailEntity projectDetailEntity = projectService.projectDetailInfo(projectDetailId);
        model.addAttribute("detail",projectDetailEntity);
        model.addAttribute("companyId",companyId);
        return url;
    }

    @GetMapping("/users/{userId}/projects/{projectId}/details/{projectDetailId}/edit")
    public String updateProjectDetail(@PathVariable Long userId, @PathVariable Long projectId, @PathVariable Long projectDetailId,Principal principal, Model model) {
        Long companyId = Long.valueOf(urlService.findCompanyUrl(principal.getName()));
        String url = "company/project/updateProjectDetail.html";
        if(!urlService.isValidUser(principal.getName(),model)) {
            url = "redirect:/manageus/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }

        ProjectDetailEntity projectDetailEntity = projectService.projectDetailInfo(projectDetailId);
        model.addAttribute("detail",projectDetailEntity);
        model.addAttribute("companyId",companyId);
        return url;
    }

    @PostMapping("/users/{userId}/projects/{projectId}/details/{projectDetailId}")
    public String updateProjectDetailPro(Long detailUserId,ProjectDetailDTO projectDetailDTO,@PathVariable Long userId, @PathVariable Long projectId, @PathVariable Long projectDetailId, Principal principal, Model model){
        Long companyId = Long.valueOf(urlService.findCompanyUrl(principal.getName()));
        String url = "company/project/updateProjectDetailPro.html";
        projectDetailDTO.setId(projectDetailId);
        projectDetailDTO.setUserId(detailUserId);
        if(!urlService.isValidUser(principal.getName(),model)) {
            url = "redirect:/manageus/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }

        projectService.updateProjectDetail(projectDetailDTO);
        model.addAttribute("companyId",companyId);

        return url;
    }




}
