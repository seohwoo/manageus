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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/getCompanyMember")
    public ResponseEntity<Map<String, Object>> getCompanyMember(Long companyId, Long myId){
        System.out.println("companyId ========"+companyId);
        System.out.println("myId ==============="+myId);
        Map<String, Object> response = new HashMap<>();
        List<HashMap<Long,String>> companyMemberList = new ArrayList<>();
        List<UserEntity> userEntityList = projectService.getCompanyUser(companyId);

        for(UserEntity s : userEntityList){
            System.out.println("반복문안아이디 ==============="+s.getId());
            System.out.println("myId ==============="+myId);
            if(!s.getId().equals(myId)) {
                UserInfoEntity userInfo = projectService.getUserInfo(s.getId());
                if (userInfo != null) {
                    HashMap<Long, String> companyMemberHash = new HashMap<>();
                    companyMemberHash.put(userInfo.getId(), userInfo.getName());
                    companyMemberList.add(companyMemberHash);
                }
            }
        }
        response.put("companyMemberList", companyMemberList);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getProjectDetailNames")
    public ResponseEntity<List<Map<String, Object>>> getProjectDetails(@RequestBody List<Map<String, Object>> projectDetails) {
        List<Map<String, Object>> response = new ArrayList<>();

        for (Map<String, Object> detail : projectDetails) {
            Long userId = Long.valueOf(detail.get("userId").toString());
            Long statusId = Long.valueOf(detail.get("statusId").toString());
            int rowIndex = Integer.parseInt(detail.get("rowIndex").toString());

            String userName = projectService.getUserInfo(userId).getName();  // userService에서 userId로 userName 조회
            String statusName = projectService.getStatus(statusId).getName();  // statusService에서 statusId로 statusName 조회

            Map<String, Object> responseDetail = new HashMap<>();
            responseDetail.put("rowIndex", rowIndex);
            responseDetail.put("userName", userName);
            responseDetail.put("statusName", statusName);

            response.add(responseDetail);
        }

        return ResponseEntity.ok(response);
    }

}
