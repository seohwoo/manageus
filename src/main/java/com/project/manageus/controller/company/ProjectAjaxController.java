package com.project.manageus.controller.company;

import com.project.manageus.dto.UserDTO;
import com.project.manageus.entity.ProjectMemberEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.entity.UserInfoEntity;
import com.project.manageus.service.ProjectService;
import com.project.manageus.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ajax/project/*")
public class ProjectAjaxController {
    private final UrlService urlService;
    private final ProjectService projectService;

    @Autowired
    public ProjectAjaxController(UrlService urlService, ProjectService projectService) {
        this.urlService = urlService;
        this.projectService = projectService;
    }

    @GetMapping("/getProjectMember")
    public ResponseEntity<Map<String, Object>> getProjectMember(Long projectId){
        Map<String, Object> response = new HashMap<>();

        List<ProjectMemberEntity> projectMemberEntityList = projectService.projectMemberList(projectId);
        List<HashMap<Long,String>> projectMemberList = new ArrayList<>();

        for(ProjectMemberEntity s : projectMemberEntityList){
            Long id = s.getUserId();
            UserInfoEntity userInfo = projectService.getUserInfo(id);
            if(userInfo != null) {
                HashMap<Long,String> projectMemberHash = new HashMap<>();
                projectMemberHash.put(userInfo.getId(), userInfo.getName());
                projectMemberList.add(projectMemberHash);
            }
        }

        response.put("projectMemberList", projectMemberList);

        return ResponseEntity.ok().body(response);
    }
}
